/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.domain.dto;

import com.hekiraku.gemini.domain.base.SerializableEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bytedance<bytedance @ bytedance.com>
 * @task
 * @date 04/17/2020 3:09 下午
 */
@Setter
@Getter
public class TextWriteDto extends SerializableEntity {
    /**
     * 人格
     */
    private String soulChar;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 文章标题
     */
    private String textTitle;
    /**
     * 文章简介
     */
    private String textSummary;
    /**
     * 文章内容
     */
    private String textDetail;
    /**
     * 文章id
     */
    private Long textId;
    /**
     * 创建日期
     */
    private String createDay;
}
