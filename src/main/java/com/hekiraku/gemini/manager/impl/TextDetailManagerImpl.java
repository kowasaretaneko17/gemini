/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.manager.impl;

import com.hekiraku.gemini.domain.entity.TextDetailEntity;
import com.hekiraku.gemini.manager.TextDetailManager;
import com.hekiraku.gemini.mapper.TextDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author bytedance<bytedance @ bytedance.com>
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

}
