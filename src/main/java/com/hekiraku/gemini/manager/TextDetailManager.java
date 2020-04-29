/**
 * *****************************************************
 * Copyright (C) 2020 geminiif.com.cn. All Rights Reserved
 * This file is part of gemini center tech project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.manager;

import com.hekiraku.gemini.domain.entity.TextDetailEntity;

/**
 * @author hekiraku<hekiraku@foxmail.com>
 * @task
 * @date 04/22/2020 4:45 下午
 */
public interface TextDetailManager {
    /**
     * 插入/更新textUser表
     */
    void createOrUpdateTextDetail(TextDetailEntity textDetailEntity);

    /**
     * 获取具体的文章内容
     * @param textId
     * @return
     */
    TextDetailEntity selectByTextId(Long textId);

}
