package com.hekiraku.gemini.service;

import com.hekiraku.gemini.common.ApiResult;

/**
 * 根据日期/性格等获取相应的数据
 */
public interface DataRecordService {
    /**
     * 获取某年全年的日记记录索引
     */
    ApiResult getYearsDiary(String years);
}
