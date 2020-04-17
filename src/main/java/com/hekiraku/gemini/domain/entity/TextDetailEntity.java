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
 * @author bytedance<bytedance @ bytedance.com>
 * @task
 * @date 04/17/2020
 */
@Setter
@Getter
@Builder
@ToString(callSuper=true)
public class TextDetailEntity extends BaseEntity {
    /**
     * 文章id
     */
    private Long textId;
    /**
     * 文章内容
     */
    private String textDetail;
}
