package com.hekiraku.gemini.service;

import com.github.pagehelper.PageInfo;
import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.domain.dto.PageParamsDto;
import com.hekiraku.gemini.domain.dto.TextReadDto;
import com.hekiraku.gemini.domain.dto.TextWriteDto;
import com.hekiraku.gemini.domain.entity.TextDetailEntity;
import com.hekiraku.gemini.domain.vo.TextDetailVo;
import com.hekiraku.gemini.domain.vo.TextSummaryEntityVo;
import com.hekiraku.gemini.domain.vo.TextUserVo;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/4/4
 * 功能说明：
 */
public interface TextRecordService {
    /**
     * 写日记，如果已有日记则更新，若没有则插入
     */
    ApiResult<TextUserVo> writeRecord(TextWriteDto textWriteDto) throws Exception;
    /**
     * 读日记
     */
    ApiResult<TextUserVo> readRecord(TextReadDto textReadDto) throws Exception;
    /**
     * 社区用：根据日期，人格查找当天开放日记
     * @return
     */
    ApiResult<PageInfo<TextSummaryEntityVo>> selectOpenTextByCreateDayAndSoulChar(TextReadDto textReadDto, PageParamsDto pageParamsDto) throws Exception;
    /**
     * 社区用：根据textId获取当天开放日记
     */
    ApiResult<TextDetailVo> selectTextDetailByTextId(Long textId);
}
