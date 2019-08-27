package com.hekiraku.gemini.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.hekiraku.gemini.aop.jwt.JWTUtil;
import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.aop.logs.LogAgent;
import com.hekiraku.gemini.common.enums.LogActiveNameEnums;
import com.hekiraku.gemini.common.enums.LogActiveProjectEnums;
import com.hekiraku.gemini.common.enums.LogActiveTypeEnums;
import com.hekiraku.gemini.entity.dto.UserInfoDto;
import com.hekiraku.gemini.entity.vo.KaptchaVo;
import com.hekiraku.gemini.entity.vo.UserInfoVo;
import com.hekiraku.gemini.mapper.UserMapper;
import com.hekiraku.gemini.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import static com.hekiraku.gemini.common.enums.AuthResultEnums.AUTH_KAPTCHA;
import static com.hekiraku.gemini.common.enums.AuthResultEnums.AUTH_LOGIN;
import static com.hekiraku.gemini.common.enums.AuthResultEnums.AUTH_LOGIN_PARAM;

/**
 * 构建组：大道金服科技部
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
            String userName=userInfoDto.getUserName();
            UserInfoVo userInfoVo = userMapper.selectByUserName(userName);
            if(null == userInfoVo || !userInfoVo.getPassword().equals(userInfoDto.getPassword())){
                return ApiResult.buildFail(AUTH_LOGIN_PARAM.getCode(), AUTH_LOGIN_PARAM.getDesc());
            } else {
                String tokenStr = JWTUtil.sign(userInfoVo);
                //相当于存入token的时候，同时存入了用户的基本信息在redis里面，然后之后在redis没有过期的时候，可以直接去redis里面拿，不用解析token，也不用threadLocal。
                //用户信息在有修改的时候要更新一次。
                userService.addTokenToRedis(userInfoVo.getUserNum(),tokenStr);
                userService.addUserInfoToRedis(userInfoVo.getUserNum(),userInfoVo);
                return ApiResult.buildSuccessNormal("登录成功",tokenStr);
            }
        } catch (Exception e) {
            log.info("登录失败，参数:{},异常：{}",userInfoDto,e);
            return ApiResult.buildFail(AUTH_LOGIN.getCode(), AUTH_LOGIN.getDesc());
        }finally {
            LogAgent.log(LogActiveProjectEnums.GEMINI,LogActiveTypeEnums.SYSTEM,userMapper.selectByUserName(userInfoDto.getUserName()).getUserNum(),LogActiveNameEnums.LOG_LOGIN,"登录");
        }
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
