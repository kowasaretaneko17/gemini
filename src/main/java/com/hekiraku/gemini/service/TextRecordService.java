package com.hekiraku.gemini.service;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.entity.TextRecordEntity;
import com.hekiraku.gemini.entity.dto.TextRecordDto;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/4
 * 功能说明：
 */
public interface TextRecordService {
    /**
     * 插入一篇日记
     * @return
     */
    ApiResult create(TextRecordEntity textRecordEntity);

    /**
     * 更新一篇日记
     * @param textRecordEntity
     * @return
     */
    ApiResult update(TextRecordEntity textRecordEntity);

    /**
     * 软删除一篇日记
     */
    ApiResult deleteSoft(TextRecordEntity textRecordEntity);

    /**
     * 根据日期，用户编号，人格状态获取日记
     */
    ApiResult selectTextByDayUsrChar(TextRecordDto textRecordDto);

    /**
     * 写日记，如果已有日记则更新，若没有则插入
     */
    ApiResult writeRecord(TextRecordDto textRecordDto,String token) throws Exception;
    /**
     * 写日记，如果已有日记则更新，若没有则插入
     */
    ApiResult readRecord(TextRecordDto textRecordDto,String token) throws Exception;
}
