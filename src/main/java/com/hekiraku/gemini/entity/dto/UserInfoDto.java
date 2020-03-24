package com.hekiraku.gemini.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/3/22
 * 功能说明：
 */
@Data
@ApiModel(value = "UserInfoDto",description = "用户传参对象dto")
public class UserInfoDto {
    @ApiModelProperty(required = true,notes = "用户名",example = "zhangsan")
    private String userName;
    @ApiModelProperty(required = true,notes = "密码",example = "12345")
    private String password;
    @ApiModelProperty(required = true,notes = "手机号码",example = "13720658539")
    private String phone;
    @ApiModelProperty(required = true,notes = "邮箱",example = "1239407570@qq.com")
    private String email;
    @ApiModelProperty(required = true,notes = "昵称",example = "法外狂徒")
    private String nickName;
}
