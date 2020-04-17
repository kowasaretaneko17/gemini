package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.entity.RoleEntity;
import com.hekiraku.gemini.entity.vo.RoleVo;
import com.hekiraku.gemini.provider.RoleDynaSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/23
 * 功能说明：
 */
public interface RoleMapper {

    @SelectProvider(type = RoleDynaSqlProvider.class,method = "selectById")
    @Results(id = "roleMap", value = {
            @Result(property = "resources",column = "id",javaType = List.class,many = @Many(select = "com.hekiraku.gemini.mapper.ResourceMapper.selectByRoleId"))
    })
    RoleVo selectById(Long id);

    @SelectProvider(type=RoleDynaSqlProvider.class,method = "selectIdByRoleName")
    Long selectIdByRoleName(String roleName);
}
