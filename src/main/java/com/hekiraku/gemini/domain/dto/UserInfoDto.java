package com.hekiraku.gemini.domain.dto;

import com.hekiraku.gemini.domain.base.SerializableEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/3/22
 * 功能说明：
 */
@Setter
@Getter
@ApiModel(value = "UserInfoDto",description = "用户传参对象dto")
public class UserInfoDto extends SerializableEntity {
    @ApiModelProperty(notes = "用户id",example = "")
    private Long userId;
    @ApiModelProperty(notes = "账号",example = "zhangsan")
    private String identityCode;
    @ApiModelProperty(notes = "密码",example = "12345")
    @NotBlank(message = "密码不可为空")
    private String password;
    @ApiModelProperty(notes = "手机号码",example = "13720658539")
    private String phone;
    @ApiModelProperty(notes = "邮箱",example = "1239407570@qq.com")
    @NotBlank(message = "邮箱不可为空")
    private String email;
    @ApiModelProperty(notes = "昵称",example = "法外狂徒张三")
    private String nickName;
    @ApiModelProperty(notes = "验证码",example = "")
    private String checkCode;
}
