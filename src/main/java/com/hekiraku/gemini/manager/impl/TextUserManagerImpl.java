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
import com.hekiraku.gemini.domain.entity.TextUserEntity;
import com.hekiraku.gemini.domain.vo.SoulCharDateVo;
import com.hekiraku.gemini.domain.vo.TextUserVo;
import com.hekiraku.gemini.manager.TextUserManager;
import com.hekiraku.gemini.mapper.TextUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hekiraku<hekiraku@foxmail.com>
 * @task
 * @date 04/22/2020 4:46 下午
 */
@Component
public class TextUserManagerImpl implements TextUserManager {
    @Autowired
    TextUserMapper textUserMapper;

    @Override
    public void createOrUpdateTextUser(TextUserEntity textUserEntity) {
        int res = textUserMapper.updateTextUser(textUserEntity);
        if(res>0){
            return;
        }
        textUserMapper.createTextUser(textUserEntity);
    }

    @Override
    public TextUserVo selectTextByTextReadDto(TextReadDto textReadDto) {
        return textUserMapper.selectTextByTextReadDto(textReadDto);
    }

    @Override
    public TextUserEntity selectTextByDayUsrChar(TextReadDto textReadDto) {
        return textUserMapper.selectTextByDayUsrChar(textReadDto);
    }

    @Override
    public List<SoulCharDateVo> selectSoulDiaryByUserAndYear(String years, Long userId) {
        return textUserMapper.selectSoulDiaryByUserAndYear(years,userId);
    }
}
