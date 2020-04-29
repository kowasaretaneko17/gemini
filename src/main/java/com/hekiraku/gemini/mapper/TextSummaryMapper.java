/**
 * *****************************************************
 * Copyright (C) 2020 geminiif.com.cn. All Rights Reserved
 * This file is part of gemini center tech project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.mapper;
import com.hekiraku.gemini.domain.dto.TextReadDto;
import com.hekiraku.gemini.domain.entity.TextSummaryEntity;
import com.hekiraku.gemini.domain.vo.TextSummaryEntityVo;
import com.hekiraku.gemini.domain.vo.TextSummaryVo;

import java.util.List;

/**
 * 文章简述表
 *
 * @author hekiraku<hekiraku@foxmail.com>
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
    /**
     * 根据创建日期和人格查找日记
     */
    List<TextSummaryEntityVo> selectOpenTextByCreateDayAndSoulChar(TextReadDto textReadDto);

}
