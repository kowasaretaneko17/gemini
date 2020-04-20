package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.domain.vo.RoleVo;
import com.hekiraku.gemini.mapper.provider.RoleDynaSqlProvider;
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

    @Results(id = "resourceMap", value = {
            @Result(property = "resources",column = "resourceId",javaType = List.class,many = @Many(select = "com.hekiraku.gemini.mapper.ResourceMapper.selectByResourceId"))
    })
    RoleVo selectByRoleId(Long roleId);

    Long selectRoleIdByRoleName(String roleName);
}
