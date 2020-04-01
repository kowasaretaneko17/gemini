package com.hekiraku.gemini.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
    @NotBlank(message = "密码不可为空")
    private String password;
    @ApiModelProperty(required = true,notes = "手机号码",example = "13720658539")
    private String phone;
    @ApiModelProperty(required = true,notes = "邮箱",example = "1239407570@qq.com")
    @NotBlank(message = "邮箱不可为空")
    private String email;
    @ApiModelProperty(required = true,notes = "昵称",example = "法外狂徒张三")
    private String nickName;
    @ApiModelProperty(required = true,notes = "验证码",example = "")
    private String checkCode;
}
