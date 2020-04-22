/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.mapper;
import com.hekiraku.gemini.domain.entity.TextSummaryEntity;
import com.hekiraku.gemini.domain.vo.TextSummaryVo;

/**
 * 文章简述表
 *
 * @author bytedance<bytedance @ bytedance.com>
 * @task
 * @date 04/17/2020 5:57 下午
 */
public interface TextSummaryMapper {
    /**
     * 通过textId获取文章简述内容
     * @param textId
     * @return
     */
    public TextSummaryEntity selectTextSummaryByTextId(Long textId);

    /**
     * 通过textId获取文章简述+详细内容
     * @param textId
     * @return
     */

    public TextSummaryVo selectByTextId(Long textId);

    /**
     * 插入一条信息
     * @param textSummaryEntity
     * @return
     */
    public int createTextSummary(TextSummaryEntity textSummaryEntity);
    /**
     * 更新一条信息
     */
    public int updateTextSummary(TextSummaryEntity textSummaryEntity);


}
