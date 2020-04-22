/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.manager;

import com.hekiraku.gemini.domain.dto.TextReadDto;
import com.hekiraku.gemini.domain.entity.TextUserEntity;
import com.hekiraku.gemini.domain.vo.SoulCharDateVo;
import com.hekiraku.gemini.domain.vo.TextUserVo;

import java.util.List;

/**
 * @author bytedance<bytedance @ bytedance.com>
 * @task
 * @date 04/22/2020 4:44 下午
 */
public interface TextUserManager {
    /**
     * 插入/更新textUser表
     */
    void createOrUpdateTextUser(TextUserEntity textUserEntity);
    /**
     * 根据日期，用户编号，人格状态获取日记
     */
    TextUserVo selectTextByTextReadDto(TextReadDto textReadDto);
    /**
     * 根据日期，用户编号，人格状态获取日记
     */
    TextUserEntity selectTextByDayUsrChar(TextReadDto textReadDto);
    /**
     * 获取全年的写了日记的日期，及性格，用做展示
     */
    List<SoulCharDateVo> selectSoulDiaryByUserAndYear(String years, Long userId);
}
