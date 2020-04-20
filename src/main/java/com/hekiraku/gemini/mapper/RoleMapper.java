package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.domain.entity.RoleEntity;
import com.hekiraku.gemini.domain.vo.RoleVo;
import com.hekiraku.gemini.mapper.provider.RoleDynaSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 构建组：
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/23
 * 功能说明：
 */
public interface RoleMapper {

    @Results(id = "roleMap", value = {
            @Result(property = "resources",column = "roleId",javaType = List.class,many = @Many(select = "com.hekiraku.gemini.mapper.ResourceMapper.selectByRoleId"))
    })
    RoleVo selectByRoleId(Long roleId);

    RoleVo selectByRoleCode(String roleCode);

    int createOrUpdateRole(RoleEntity roleEntity);
}
