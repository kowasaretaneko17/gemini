/**
 * *****************************************************
 * Copyright (C) 2020 geminiif.com.cn. All Rights Reserved
 * This file is part of gemini center tech project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hekiraku.gemini.domain.base.SerializableEntity;
import lombok.*;
/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/3/22
 * 功能说明：
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TextSummaryVo extends SerializableEntity {
    /**
     * 文章id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long textId;
    /**
     * 文章标题
     */
    private String textTitle;
    /**
     * 文章简介
     */
    private String textSummary;
    /**
     * 文章所属人格
     */
    private String soulChar;
    /**
     * 创建日期
     */
    private String createDay;
    /**
     * 文章内容
     */
    private TextDetailVo textDetail;
}
