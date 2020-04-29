/**
 * *****************************************************
 * Copyright (C) 2020 geminiif.com.cn. All Rights Reserved
 * This file is part of gemini center tech project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.manager.impl;

import com.hekiraku.gemini.domain.dto.TextReadDto;
import com.hekiraku.gemini.domain.entity.TextSummaryEntity;
import com.hekiraku.gemini.domain.vo.TextSummaryEntityVo;
import com.hekiraku.gemini.manager.TextSummaryManager;
import com.hekiraku.gemini.mapper.TextSummaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hekiraku<hekiraku@foxmail.com>
 * @task
 * @date 04/22/2020 4:47 下午
 */
@Component
public class TextSummaryManagerImpl implements TextSummaryManager {

    @Autowired
    TextSummaryMapper textSummaryMapper;

    @Override
    public void createOrUpdateTextSummary(TextSummaryEntity textSummaryEntity) {
        int res = textSummaryMapper.updateTextSummary(textSummaryEntity);
        if(res>0){
            return;
        }
        textSummaryMapper.createTextSummary(textSummaryEntity);
    }

    @Override
    public List<TextSummaryEntityVo> selectOpenTextByCreateDayAndSoulChar(TextReadDto textReadDto) {
        return textSummaryMapper.selectOpenTextByCreateDayAndSoulChar(textReadDto);
    }
}
