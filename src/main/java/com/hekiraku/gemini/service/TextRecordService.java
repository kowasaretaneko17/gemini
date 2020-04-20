package com.hekiraku.gemini.service;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.domain.dto.TextReadDto;
import com.hekiraku.gemini.domain.dto.TextWriteDto;
import com.hekiraku.gemini.domain.entity.TextUserEntity;
import com.hekiraku.gemini.domain.vo.TextUserVo;

import java.util.List;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
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
}
