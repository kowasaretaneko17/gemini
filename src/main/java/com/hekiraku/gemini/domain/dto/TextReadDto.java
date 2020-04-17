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
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 读日记传参
 *
 * @author bytedance<bytedance @ bytedance.com>
 * @task
 * @date 04/17/2020
 */
@Getter
@Setter
public class TextReadDto extends SerializableEntity {
    /**
     * 人格
     */
    private String soulChar;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 日期
     */
    private Date createDay;
}
