/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.domain.entity;

import com.hekiraku.gemini.domain.base.BaseEntity;
import lombok.*;

/**
 * @author weiyimeng<weiyimeng @ bytedance.com>
 * @task
 * @date 04/17/2020
 */
@Setter
@Getter
@Builder
@ToString(callSuper=true)
public class TextSummaryEntity extends BaseEntity {
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
}
