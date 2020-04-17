package com.hekiraku.gemini.manager;

import com.hekiraku.gemini.domain.entity.TextUserEntity;
import com.hekiraku.gemini.domain.vo.SoulCharVo;

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
    int create(TextUserEntity textRecordEntity);

    /**
     * 更新一篇日记
     * @param textRecordEntity
     * @return
     */
    int update(TextUserEntity textRecordEntity);

    /**
     * 软删除一篇日记
     */
    int deleteSoft(TextUserEntity textRecordEntity);

    /**
     * 根据日期，用户编号，人格状态获取日记
     */
    TextUserEntity selectTextByDayUsrChar(TextDto textRecordDto);
    /**
     * 根据日期，用户编号，人格状态获取日记,返回list
     */
    List<TextUserEntity> selectTextByDayUsrCharList(TextDto textRecordDto);
    /**
     * 获取全年的写了日记的日期，及性格，用做展示
     */
    List<List<SoulCharVo>> selectSoulDiaryByUserAndYear(String years, String userNum);
}
