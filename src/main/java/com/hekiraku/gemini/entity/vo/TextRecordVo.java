package com.hekiraku.gemini.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
/**
 * 构建组：大道金服科技部
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
@ApiModel(value = "TextRecordVo",description = "日记返回信息")
public class TextRecordVo implements Serializable {
    @ApiModelProperty(notes = "日记内容")
    private String text;
    @ApiModelProperty(notes = "人格",allowableValues = "ura,omote")
    private String soulChar;
    @ApiModelProperty(notes = "创建日期",example = "2019-04-04")
    private String createDay;
}
