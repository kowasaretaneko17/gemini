package com.hekiraku.gemini.controller.auth;

import com.hekiraku.gemini.common.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/23
 * 功能说明：
 */
@RestController
@Api(value="权限模块", tags="权限模块")
@RequestMapping("/user")
public class AuthUserController {
    @ApiOperation(value="用户权限信息", notes = "用户权限信息获取")
    @GetMapping("/getMessage")
    public ApiResult submitLogin() {
        return ApiResult.successMsg("您拥有用户权限，可以获得该接口的信息！！");
    }
}
