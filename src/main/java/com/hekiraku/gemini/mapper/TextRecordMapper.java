package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.entity.TextRecordEntity;
import com.hekiraku.gemini.entity.dto.TextRecordDto;
import com.hekiraku.gemini.entity.vo.SoulCharDateVo;
import com.hekiraku.gemini.provider.TextRecordDynaSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/4
 * 功能说明：
 */
public interface TextRecordMapper {
    /**
     * 插入一篇日记
     * @return
     */
    @InsertProvider(type=TextRecordDynaSqlProvider.class,method="create")
    int create(TextRecordEntity textRecordEntity);

    /**
     * 更新一篇日记
     * @param textRecordEntity
     * @return
     */
    @UpdateProvider(type=TextRecordDynaSqlProvider.class,method = "update")
    int update(TextRecordEntity textRecordEntity);

    /**
     * 软删除一篇日记
     */
    @UpdateProvider(type=TextRecordDynaSqlProvider.class,method = "deleteSoft")
    int deleteSoft(TextRecordEntity textRecordEntity);

    /**
     * 根据日期，用户编号，人格状态获取日记
     */
    @SelectProvider(type=TextRecordDynaSqlProvider.class,method = "selectTextByDayUsrChar")
    TextRecordEntity selectTextByDayUsrChar(TextRecordDto textRecordDto);

    /**
     * 获取全年的写了日记的日期，及性格，用做展示
     */
    @SelectProvider(type=TextRecordDynaSqlProvider.class,method = "selectSoulDiaryByUserAndYear")
    List<SoulCharDateVo> selectSoulDiaryByUserAndYear(String years, String userNum);
    /**
     * 获取存在日记的年份
     */
    List<String> selectYearDiaryByUser(String userNum);
}
