package com.hekiraku.gemini.service;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.domain.vo.ActiveLogVo;

import java.util.List;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/4/3
 * 功能说明：
 */
public interface ActiveLogService {
    public ApiResult<List<ActiveLogVo>> selectAllByActiveName(String activeName);
}
