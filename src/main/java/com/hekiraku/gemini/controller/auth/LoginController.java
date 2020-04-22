package com.hekiraku.gemini.controller.auth;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.hekiraku.gemini.aop.jwt.JWTUtil;
import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.aop.logs.LogAgent;
import com.hekiraku.gemini.common.enums.LogActiveNameEnums;
import com.hekiraku.gemini.common.enums.LogActiveProjectEnums;
import com.hekiraku.gemini.common.enums.LogActiveTypeEnums;
import com.hekiraku.gemini.domain.entity.UserEntity;
import com.hekiraku.gemini.domain.dto.MailDto;
import com.hekiraku.gemini.domain.dto.UserInfoDto;
import com.hekiraku.gemini.domain.vo.KaptchaVo;
import com.hekiraku.gemini.domain.vo.UserInfoVo;
import com.hekiraku.gemini.mapper.UserMapper;
import com.hekiraku.gemini.service.UserService;
import com.hekiraku.gemini.utils.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import static com.hekiraku.gemini.common.enums.AuthResultEnums.*;
import static com.hekiraku.gemini.common.enums.MailEnums.M_HEKIRAKU_SOURCE;
import static com.hekiraku.gemini.utils.DESUtils.MD5;

/**
 * 构建组：
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/23
 * 功能说明：
 */
@RestController
@Api(value = "登录模块", tags = "登录模块")
@Slf4j
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultKaptcha producer;

    @ApiOperation(value = "未登录", notes = "未登录")
    @GetMapping("/notLogin")
    public ApiResult notLogin() {
        return ApiResult.successMsg("您尚未登陆");
    }

    @ApiOperation(value = "无权限", notes = "无权限")
    @GetMapping("/notRole")
    public ApiResult notRole() {
        return ApiResult.successMsg("您没有权限！");
    }

    @ApiOperation(value = "登出", notes = "登出")
    @GetMapping("/logout")
    public ApiResult logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return ApiResult.successMsg("成功注销！");
    }

    /**
     * 登陆
     * shiro+jwt登录。
     *
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "用户登录接口")
    @ApiResponses({
            @ApiResponse(code = 80000,message = "登录失败",response = ApiResult.class),
            @ApiResponse(code = 80001,message = "用户名或密码错误",response = ApiResult.class)
    })
    public ApiResult login(@RequestBody UserInfoDto userInfoDto) {
        try {
            log.info("登录,参数为：",userInfoDto.toString());
            String identityCode=userInfoDto.getIdentityCode();
            UserInfoVo userInfoVo = userMapper.selectByIdentityCode(identityCode);
            String password = MD5(MD5(userInfoDto.getPassword()+"gemini_hekiraku_wanlly"));
            if(null == userInfoVo || !userInfoVo.getPassword().equals(password)){
                return ApiResult.buildFail(AUTH_LOGIN_PARAM.getCode(), AUTH_LOGIN_PARAM.getDesc());
            } else {
                String tokenStr = JWTUtil.sign(userInfoVo);
                //相当于存入token的时候，同时存入了用户的基本信息在redis里面，然后之后在redis没有过期的时候，可以直接去redis里面拿，不用解析token，也不用threadLocal。
                //用户信息在有修改的时候要更新一次。
                userService.addTokenToRedis(userInfoVo.getUserId().toString(),tokenStr);
                userService.addUserInfoToRedis(userInfoVo.getUserId().toString(), JSON.toJSONString(userInfoVo));
                return ApiResult.buildSuccessNormal("登录成功",tokenStr);
            }
        } catch (Exception e) {
            log.error("登录失败，参数:{},异常：{}",userInfoDto,e);
            return ApiResult.buildFail(AUTH_LOGIN.getCode(), AUTH_LOGIN.getDesc());
        }finally {
            LogAgent.log(LogActiveProjectEnums.GEMINI,LogActiveTypeEnums.SYSTEM,userMapper.selectByIdentityCode(userInfoDto.getIdentityCode()).getUserId().toString(),LogActiveNameEnums.LOG_LOGIN,"登录");
        }
    }
    /**
     * 注册账户
     */
    @PostMapping("/register")
    @ApiOperation(value = "注册", notes = "用户注册接口")
    @ApiResponses({
            @ApiResponse(code = 80002,message = "用户名重复",response = ApiResult.class),
            @ApiResponse(code = 80003,message = "邮箱重复",response = ApiResult.class),
            @ApiResponse(code = 80004,message = "手机号重复",response = ApiResult.class)
    })
    public ApiResult register(@RequestBody @Valid UserInfoDto userInfoDto){
        try {
            //检查邮箱是否存在
            if(userMapper.selectByEmail(userInfoDto.getEmail())!=null){
                return ApiResult.successMsg("邮箱已存在");
            }
            if(StringUtils.isEmpty(userInfoDto.getCheckCode())){
                return ApiResult.successMsg("请填写验证码");
            }
            if(!userService.signCheckCode(userInfoDto.getEmail(),userInfoDto.getCheckCode())){
                return ApiResult.successMsg("验证不通过，请检查验证码");
            }
            UserEntity userEntity = UserEntity.builder().build();
            BeanUtils.copyNotNullProperties(userEntity,userInfoDto);
            Long userId = SnowFlakeUtils.nextId();
            userEntity.setUserId(userId);
            userService.createOrUpdateUser(userEntity);
            return ApiResult.successMsg("注册成功");
        }catch (Exception e){
            log.error("注册失败:",e);
            return ApiResult.buildFail(AUTH_REGISTER.getCode(),AUTH_REGISTER.getDesc());
        }

    }
    /**
     * 修改密码
     * 注册账号
     * 需要验证邮箱验证码
     */
    @PostMapping("/checkEmail")
    @ApiOperation(value = "邮箱验证", notes = "邮箱验证接口")
    public ApiResult checkEmail(String email){
        if(StringUtils.isEmpty(email)){
            return ApiResult.successMsg("邮箱不能为空");
        }
        int code = CheckCodeUtils.sixLength();
        MailDto mailDto = new MailDto();
        mailDto.setMailSource(M_HEKIRAKU_SOURCE.getCode());
        mailDto.setMailTarget(email);
        mailDto.setSubject("欢迎注册gemini星云总线");
        mailDto.setContent("您的注册验证码是：     "+ code);
        MailUtils.sendMail(mailDto);
        userService.addCheckCode(email,String.valueOf(code));
        return ApiResult.successMsg("发送成功");
    }

    /**
     * 生成验证码
     *
     * @return
     */
    @PostMapping("/captcha")
    @ResponseBody
    @ApiOperation(value = "验证码", notes = "生成图片验证码")
    @ApiResponses({
            @ApiResponse(code = 80005,message = "生成验证码失败",response = ApiResult.class)
    })
    public ApiResult<KaptchaVo> captcha() {
        try {
            // 生成文字验证码
            String text = producer.createText();
            // 生成图片验证码
            ByteArrayOutputStream outputStream = null;
            BufferedImage image = producer.createImage(text);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            // 对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            //保存到redis
            KaptchaVo kaptchaVo = userService.createRandomToken(text);
            kaptchaVo.setImg(encoder.encode(outputStream.toByteArray()));
            return ApiResult.buildSuccessNormal("生成验证码成功",kaptchaVo);
        } catch (Exception e) {
            log.error("生成验证码失败异常：{}",e);
            return ApiResult.buildFail(AUTH_KAPTCHA.getCode(),AUTH_KAPTCHA.getDesc());
        }
    }
}
