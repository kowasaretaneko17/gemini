package com.hekiraku.gemini.entity.vo;

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
public class TextRecordVo implements Serializable {
    private String text;
    private String soulChar;
    private String createDay;
}
