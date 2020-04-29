/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.domain.vo;

import com.hekiraku.gemini.domain.base.BaseVo;
import com.hekiraku.gemini.domain.base.SerializableEntity;
import com.hekiraku.gemini.domain.entity.TextSummaryEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2020/4/29
 * 功能说明：文章简介vo类
 */
@Setter
@Getter
@Builder
@ToString(callSuper = true)
public class TextSummaryEntityVo extends BaseVo {
    /**
     * 文章id
     */
    private String textId;
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
