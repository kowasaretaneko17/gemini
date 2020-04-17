package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.domain.entity.ActiveLogEntity;
import com.hekiraku.gemini.domain.vo.ActiveLogVo;
import com.hekiraku.gemini.provider.ActiveLogDynaSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/3
 * 功能说明：
 */
@Mapper
public interface ActiveLogMapper {
    /**
     * 根据动作名称，查询日志
     * @param activeName
     * @return
     */
    @SelectProvider(type=ActiveLogDynaSqlProvider.class,method="selectAllByActiveName")
    List<ActiveLogVo> selectAllByActiveName(@Param(value="activeName") String activeName);

    /**
     * 插入一条操作记录
     * @param activeLogEntity
     * @return
     */
    @InsertProvider(type=ActiveLogDynaSqlProvider.class,method="create")
    int create(ActiveLogEntity activeLogEntity);
}
