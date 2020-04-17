package com.hekiraku.gemini.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/22
 * 功能说明：
 */
public class ResourceDynaSqlProvider {
    /**
     * 通过id查找资源表信息
     * @param id
     * @return
     */
    public String selectById(String id){
        SQL selectById = new SQL()
                .SELECT("*")
                .FROM("g_resource")
                .WHERE("id=#{id}");
        return selectById.toString();
    }

    /**
     * 通过角色id查找资源表信息
     * @param roleId
     * @return
     */
    public String selectByRoleId(String roleId){
        SQL selectByRoleId = new SQL()
                .SELECT("gre.*")
                .FROM("g_resource gre,g_res_role grr")
                .WHERE("grr.role_id=#{roleId}")
                .AND()
                .WHERE("grr.res_id =gre.id");
        return selectByRoleId.toString();
    }
}
