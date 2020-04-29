/**
 * *****************************************************
 * Copyright (C) 2020 geminiif.com.cn. All Rights Reserved
 * This file is part of gemini center tech project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.domain.entity.TextDetailEntity;
import com.hekiraku.gemini.domain.vo.TextDetailVo;

/**
 * 文章详情表
 *
 * @author hekiraku<hekiraku@foxmail.com>
 * @task
 * @date 04/17/2020 5:47 下午
 */
public interface TextDetailMapper {
    /**
     * 根据文章id获取文章详情
     */
    TextDetailVo selectByTextId(Long textId);
    /**
     * 插入
     */
    int createTextDetail(TextDetailEntity textDetailEntity);
    /**
     * 更新
     */
    int updateTextDetail(TextDetailEntity textDetailEntity);

}
