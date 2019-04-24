package com.hekiraku.gemini.manager;

import com.hekiraku.gemini.entity.TextRecordEntity;
import com.hekiraku.gemini.entity.dto.TextRecordDto;
import com.hekiraku.gemini.entity.vo.TextRecordVo;

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
    int create(TextRecordEntity textRecordEntity);

    /**
     * 更新一篇日记
     * @param textRecordEntity
     * @return
     */
    int update(TextRecordEntity textRecordEntity);

    /**
     * 软删除一篇日记
     */
    int deleteSoft(TextRecordEntity textRecordEntity);

    /**
     * 根据日期，用户编号，人格状态获取日记
     */
    TextRecordEntity selectTextByDayUsrChar(TextRecordDto textRecordDto);
    /**
     * 根据日期，用户编号，人格状态获取日记,返回list
     */
    List<TextRecordEntity> selectTextByDayUsrCharList(TextRecordDto textRecordDto);
}
