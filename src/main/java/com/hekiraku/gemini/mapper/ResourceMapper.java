package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.domain.vo.ResourceVo;
import com.hekiraku.gemini.mapper.provider.ResourceDynaSqlProvider;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/22
 * 功能说明：
 */
public interface ResourceMapper {
    @SelectProvider(type=ResourceDynaSqlProvider.class,method = "selectById")
    ResourceVo selectById(String id);

    @SelectProvider(type = ResourceDynaSqlProvider.class,method = "selectByRoleId")
    ResourceVo selectByRoleId(String roleId);
}
