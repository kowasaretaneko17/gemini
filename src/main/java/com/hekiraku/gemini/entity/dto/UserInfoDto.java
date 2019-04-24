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
    @ApiModelProperty(required = true,notes = "用户名",example = "zhangSan")
    private String userName;
    @ApiModelProperty(required = true,notes = "密码",example = "123")
    private String password;
}
