package com.hekiraku.gemini.manager;

import com.github.pagehelper.PageInfo;
import com.hekiraku.gemini.domain.dto.PageParamsDto;
import com.hekiraku.gemini.domain.dto.TextReadDto;
import com.hekiraku.gemini.domain.dto.TextWriteDto;
import com.hekiraku.gemini.domain.entity.TextSummaryEntity;
import com.hekiraku.gemini.domain.entity.TextUserEntity;
import com.hekiraku.gemini.domain.vo.*;

import java.util.List;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/4/4
 * 功能说明：
 */
public interface TextRecordManager {
    /**
     * 插入一篇日记
     * @return
     */
    void createOrUpdateText(TextWriteDto textWriteDto);
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
    List<List<SoulCharVo>> selectSoulDiaryByUserAndYear(String years, Long userId);
     /**
     * 分页：社区用：根据日期，人格查找当天开放日记
      * @return
      */
    PageInfo<TextSummaryEntity> selectOpenTextPageByCreateDayAndSoulChar(TextReadDto textReadDto, PageParamsDto pageParamsDto);

    /**
     * 根据textId获取用户日记
     * @param textId
     * @return
     */
    TextDetailVo selectTextDetailByTextId(Long textId);


}
