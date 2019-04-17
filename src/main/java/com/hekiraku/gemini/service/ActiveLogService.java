package com.hekiraku.gemini.service;

import com.hekiraku.gemini.common.ApiResult;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/3
 * 功能说明：
 */
public interface ActiveLogService {
    public ApiResult selectAllByActiveName(String activeName);
}
