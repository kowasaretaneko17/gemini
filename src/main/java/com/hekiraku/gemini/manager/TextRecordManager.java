package com.hekiraku.gemini.manager;

import com.hekiraku.gemini.domain.dto.TextReadDto;
import com.hekiraku.gemini.domain.dto.TextWriteDto;
import com.hekiraku.gemini.domain.entity.TextUserEntity;
import com.hekiraku.gemini.domain.vo.SoulCharVo;
import com.hekiraku.gemini.domain.vo.TextSummaryVo;
import com.hekiraku.gemini.domain.vo.TextUserVo;

import java.util.List;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
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
     * 社区用：根据日期，人格查找当天开放日记
     */
    List<TextSummaryVo> selectOpenTextByCreateDayAndSoulChar(TextReadDto textReadDto);
}
