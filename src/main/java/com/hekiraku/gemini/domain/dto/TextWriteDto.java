/**
 * *****************************************************
 * Copyright (C) 2020 geminiif.com.cn. All Rights Reserved
 * This file is part of gemini center tech project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.domain.dto;

import com.hekiraku.gemini.domain.base.BaseEntity;
import com.hekiraku.gemini.domain.base.SerializableEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hekiraku<hekiraku@foxmail.com>
 * @task
 * @date 04/17/2020 3:09 下午
 */
@Setter
@Getter
public class TextWriteDto extends BaseEntity {
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
     * 文章标题
     */
    @ApiModelProperty(required = true,notes = "标题",example = "标题")
    private String textTitle;
    /**
     * 文章简介
     */
    @ApiModelProperty(required = true,notes = "标题",example = "标题")
    private String textSummary;
    /**
     * 文章内容
     */
    @ApiModelProperty(required = true,notes = "内容",example = "内容")
    private String textDetail;
    /**
     * 文章id
     */
    @ApiModelProperty(required = false,notes = "文章id",example = "不用传")
    private Long textId;
    /**
     * 创建日期
     */
    @ApiModelProperty(required = false,notes = "创建日期",example = "不用传，或格式 yyyy-MM-dd")
    private String createDay;
}
