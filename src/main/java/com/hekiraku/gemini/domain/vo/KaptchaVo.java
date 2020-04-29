package com.hekiraku.gemini.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/4/23
 * 功能说明：
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@ApiModel(value = "KaptchaVo",description = "验证码返回")
public class KaptchaVo implements Serializable {
    @ApiModelProperty(notes = "图片验证码二进制流")
    private String img;
    @ApiModelProperty(notes = "防重复提交token")
    private String cToken;
}
