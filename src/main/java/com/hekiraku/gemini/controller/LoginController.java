package com.hekiraku.gemini.controller;

import com.hekiraku.gemini.aop.jwt.JWTUtil;
import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.common.LogAgent;
import com.hekiraku.gemini.common.enums.LogActiveNameEnums;
import com.hekiraku.gemini.common.enums.LogActiveProjectEnums;
import com.hekiraku.gemini.common.enums.LogActiveTypeEnums;
import com.hekiraku.gemini.entity.dto.UserInfoDto;
import com.hekiraku.gemini.entity.vo.UserInfoVo;
import com.hekiraku.gemini.mapper.UserMapper;
import com.hekiraku.gemini.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import static com.hekiraku.gemini.common.enums.AuthResultEnums.AUTH_LOGIN;
import static com.hekiraku.gemini.common.enums.ExceptionResultEnums.E_LOGIN;

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
    private RedisTemplate redisTemplate;
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
    public ApiResult login(@RequestBody UserInfoDto userInfoDto) {
        try {
            String userName=userInfoDto.getUserName();
            UserInfoVo userInfoVo = userMapper.selectAllByUserName(userName);
            if(null == userInfoVo || !userInfoVo.getPassword().equals(userInfoDto.getPassword())){
                return ApiResult.buildFail(AUTH_LOGIN.getCode(), AUTH_LOGIN.getDesc());
            } else {
                String tokenStr = JWTUtil.sign(userInfoDto.getUserName(), userInfoDto.getPassword());
                userService.addTokenToRedis(userInfoDto.getUserName(),tokenStr);
                return ApiResult.buildSuccessNormal("登录成功",tokenStr);
            }
        } catch (Exception e) {
            log.info("登录异常：{}",e);
            return ApiResult.buildFail(E_LOGIN.getCode(), E_LOGIN.getDesc());
        }finally {
            LogAgent.log(LogActiveProjectEnums.GEMINI,LogActiveTypeEnums.SYSTEM,userMapper.selectAllByUserName(userInfoDto.getUserName()).getUserNum(),LogActiveNameEnums.LOG_LOGIN,"登录");
        }
    }
}
