package com.hekiraku.gemini.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/23
 * 功能说明：
 */
@Controller
@Api(value="home模块", tags="home模块")
public class HomeCtroller {
//    @ApiOperation(value="登录", notes = "登录")
//    @GetMapping({"/login"})
//    public String login(){
//        return "/login";
//    }

    @ApiOperation(value="主页", notes = "主页")
    @GetMapping({"/"})
    public String index(){
        return "/index";
    }
}
