/**
 * *****************************************************
 * Copyright (C) 2020 geminiif.com.cn. All Rights Reserved
 * This file is part of gemini center tech project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.domain.vo;

import com.hekiraku.gemini.domain.base.SerializableEntity;
import lombok.*;

/**
 * @author hekiraku<hekiraku@foxmail.com>
 * @task
 * @date 04/17/2020 6:34 下午
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
