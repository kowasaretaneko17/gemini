/**
 * *****************************************************
 * Copyright (C) 2020 geminiif.com.cn. All Rights Reserved
 * This file is part of gemini center tech project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.manager;

import com.hekiraku.gemini.domain.dto.TextReadDto;
import com.hekiraku.gemini.domain.entity.TextSummaryEntity;
import com.hekiraku.gemini.domain.vo.TextSummaryEntityVo;

import java.util.List;

/**
 * @author hekiraku<hekiraku@foxmail.com>
 * @task
 * @date 04/22/2020 4:45 下午
 */
public interface TextSummaryManager {
    /**
     * 插入/更新textSummary表
     */
    void createOrUpdateTextSummary(TextSummaryEntity textSummaryEntity);

    /**
     * 根据创建日期和人格查找日记
     * @param textReadDto
     * @return
     */
    List<TextSummaryEntityVo> selectOpenTextByCreateDayAndSoulChar(TextReadDto textReadDto);

}
