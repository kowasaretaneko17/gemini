package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.domain.dto.TextReadDto;
import com.hekiraku.gemini.domain.entity.TextUserEntity;
import com.hekiraku.gemini.domain.vo.SoulCharDateVo;
import com.hekiraku.gemini.mapper.provider.TextUserDynaSqlProvider;
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
public interface TextUserMapper {
    /**
     * 插入一篇日记
     */
    @InsertProvider(type = TextUserDynaSqlProvider.class, method = "create")
    int createOrUpdateTextUser(TextUserEntity textRecordEntity);

    /**
     * 根据日期，用户编号，人格状态获取日记
     */
    @SelectProvider(type = TextUserDynaSqlProvider.class, method = "selectTextByDayUsrChar")
    TextUserEntity selectTextByDayUsrChar(TextReadDto textReadDto);

    /**
     * 获取全年的写了日记的日期，及性格，用做展示
     */
    @SelectProvider(type = TextUserDynaSqlProvider.class, method = "selectSoulDiaryByUserAndYear")
    List<SoulCharDateVo> selectSoulDiaryByUserAndYear(String years, String userNum);
}
