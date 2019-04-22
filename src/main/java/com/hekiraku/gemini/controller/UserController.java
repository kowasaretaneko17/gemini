package com.hekiraku.gemini.controller;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.entity.vo.UserInfoVo;
import com.hekiraku.gemini.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/22
 * 功能说明：
 */
@RestController
@Api(value="用户模块", tags="用户模块")
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="通过名字获取用户信息", notes = "通过名字获取用户信息")
    @GetMapping("/selectAllByUserName")
    public ApiResult selectAllByUserName(String username){
        try {
            return userService.selectByUserName(username);
        }catch (Exception e){
            log.error("{}",e);
            return ApiResult.buildFail("-9999","失败");
        }
    }
}
