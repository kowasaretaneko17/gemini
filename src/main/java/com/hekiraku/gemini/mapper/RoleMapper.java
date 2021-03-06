package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/23
 * 功能说明：
 */
public interface RoleMapper {
    @Select("select * from g_role where id = #{id}")
    RoleEntity selectById(String id);
}
