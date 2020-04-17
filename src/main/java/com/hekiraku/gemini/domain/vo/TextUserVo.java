package com.hekiraku.gemini.domain.vo;

import com.hekiraku.gemini.domain.base.SerializableEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
/**
 * 构建组：
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/4
 * 功能说明：
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@ApiModel(value = "TextUserVo",description = "日记返回信息")
public class TextUserVo extends SerializableEntity {
    private Long userId;
    private Long textId;
    private TextSummaryVo textSummaryVo;
}
