package com.hekiraku.gemini.controller;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.common.LogAgent;
import com.hekiraku.gemini.common.enums.LogActiveNameEnums;
import com.hekiraku.gemini.common.enums.LogActiveProjectEnums;
import com.hekiraku.gemini.common.enums.LogActiveTypeEnums;
import com.hekiraku.gemini.mapper.UserMapper;
import com.hekiraku.gemini.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

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
        return ApiResult.buildFail("-9999", "您尚未登陆！");
    }

    @ApiOperation(value = "无权限", notes = "无权限")
    @GetMapping("/notRole")
    public ApiResult notRole() {
        return ApiResult.successMsg("您没有权限！");
    }

    @ApiOperation(value = "主页", notes = "主页")
    @GetMapping("/index")
    public ApiResult index() {
        return ApiResult.successMsg("欢迎来到登录成功后的主页！");
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
     * shiro登录。
     *
     * @param username 用户名
     * @param password 密码
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "用户登录接口")
    public ApiResult login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ApiResult.buildFail("-9999", "用户名/密码不能为空！");
        }
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        try {
            subject.login(token);
            //根据权限，指定返回数据
            String role = userMapper.selectAllByUserName(username).getRoleName();
            if ("user".equals(role)) {
                return ApiResult.buildSuccessNormal("200", "欢迎登陆", subject.getSession().getId());
            }
            if ("admin".equals(role)) {
                return ApiResult.buildSuccessNormal("200", "欢迎来到管理员页面", subject.getSession().getId());
            }
            return ApiResult.buildFail("-9999", "权限错误！");
        } catch (Exception e) {
            log.info("{}",e);
            return ApiResult.buildFail("-9999", "登录异常！");
        }finally {
            LogAgent.log(LogActiveProjectEnums.GEMINI,LogActiveTypeEnums.SYSTEM,userMapper.selectAllByUserName(username).getUserNum(),LogActiveNameEnums.LOG_LOGIN,"登录");
        }
    }
}
