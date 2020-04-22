package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.domain.dto.TextReadDto;
import com.hekiraku.gemini.domain.entity.TextUserEntity;
import com.hekiraku.gemini.domain.vo.SoulCharDateVo;
import com.hekiraku.gemini.domain.vo.TextUserVo;

import java.util.List;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/4
 * 功能说明：
 */
public interface TextUserMapper {
    /**
     * 插入一篇日记
     */
    int createTextUser(TextUserEntity textUserEntity);

    /**
     * 更新一篇日记
     * @param textUserEntity
     * @return
     */
    int updateTextUser(TextUserEntity textUserEntity);

    /**
     * 获取日记
     */
    public TextUserVo selectTextByTextReadDto(TextReadDto textReadDto);
    /**
     * 根据日期，用户编号，人格状态获取日记
     */
    TextUserEntity selectTextByDayUsrChar(TextReadDto textReadDto);

    /**
     * 获取全年的写了日记的日期，及性格，用做展示
     */
    List<SoulCharDateVo> selectSoulDiaryByUserAndYear(String years, Long userId);
}
