/**
 * *****************************************************
 * Copyright (C) 2020 geminiif.com.cn. All Rights Reserved
 * This file is part of gemini center tech project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.manager.impl;

import com.hekiraku.gemini.domain.entity.TextDetailEntity;
import com.hekiraku.gemini.domain.vo.TextDetailVo;
import com.hekiraku.gemini.manager.TextDetailManager;
import com.hekiraku.gemini.mapper.TextDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hekiraku<hekiraku@foxmail.com>
 * @task
 * @date 04/22/2020 4:47 下午
 */
@Component
public class TextDetailManagerImpl implements TextDetailManager {

    @Autowired
    TextDetailMapper textDetailMapper;

    @Override
    public void createOrUpdateTextDetail(TextDetailEntity textDetailEntity) {
        int res = textDetailMapper.updateTextDetail(textDetailEntity);
        if(res>0){
            return;
        }
        textDetailMapper.createTextDetail(textDetailEntity);
    }

    @Override
    public TextDetailVo selectByTextId(Long textId) {
        return textDetailMapper.selectByTextId(textId);
    }

}
