/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.manager;

import com.hekiraku.gemini.domain.entity.TextSummaryEntity;

/**
 * @author bytedance<bytedance @ bytedance.com>
 * @task
 * @date 04/22/2020 4:45 下午
 */
public interface TextSummaryManager {
    /**
     * 插入/更新textSummary表
     */
    void createOrUpdateTextSummary(TextSummaryEntity textSummaryEntity);
}
