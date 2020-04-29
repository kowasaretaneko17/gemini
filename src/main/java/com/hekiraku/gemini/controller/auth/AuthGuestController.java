package com.hekiraku.gemini.controller.auth;

import com.hekiraku.gemini.common.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/1/23
 * 功能说明：
 */
@RestController
@Api(value="权限模块", tags="权限模块")
@RequestMapping("/guest")
public class AuthGuestController {

    @ApiOperation(value="游客进入", notes = "游客浏览")
    @GetMapping("/enter")
    public ApiResult login() {
        return ApiResult.successMsg("欢迎进入，您的身份是游客");
    }

    @ApiOperation(value="游客权限信息", notes = "游客权限信息获取")
    @GetMapping("/getMessage")
    public ApiResult submitLogin() {
        return ApiResult.successMsg("您拥有获得该接口的信息的权限！");
    }
}
