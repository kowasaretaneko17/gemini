package com.hekiraku.gemini.controller;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.entity.vo.UserInfoVo;
import com.hekiraku.gemini.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hekiraku.gemini.common.enums.AuthResultEnums.AUTH_USERINFO;

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
    @ApiResponses({
            @ApiResponse(code = 80006,message = "获取用户信息失败",response = ApiResult.class)
    })
    public ApiResult<UserInfoVo> selectAllByUserName(String username){
        try {
            return userService.selectByUserName(username);
        }catch (Exception e){
            log.error("通过名字获取用户信息失败：{}",e);
            return ApiResult.buildFail(AUTH_USERINFO.getCode(),AUTH_USERINFO.getDesc());
        }
    }
}