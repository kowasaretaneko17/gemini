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
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
@NoArgsConstructor
public class TextReadDto extends SerializableEntity {
    /**
     * 人格
     */
    @ApiModelProperty(required = true,notes = "人格",example = "omote或者ura")
    private String soulChar;
    /**
     * 用户id
     */
    @ApiModelProperty(required = false,notes = "用户id",example = "不用传")
    private Long userId;
    /**
     * 日期
     */
    @ApiModelProperty(required = true,notes = "创建日期",example = "格式 yyyy-MM-dd")
    private String createDay;
}
