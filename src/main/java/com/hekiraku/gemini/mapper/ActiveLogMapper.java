package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.domain.entity.ActiveLogEntity;
import com.hekiraku.gemini.domain.vo.ActiveLogVo;
import com.hekiraku.gemini.mapper.provider.ActiveLogDynaSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
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
