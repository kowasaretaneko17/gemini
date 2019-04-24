package com.hekiraku.gemini.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
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
