package com.hekiraku.gemini.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/22
 * 功能说明：
 */
public class RoleDynaSqlProvider {
    /**
     * 根据id查找角色表信息
     * @param id
     * @return
     */
    public String selectById(Long id){
        SQL selectById = new SQL()
                .SELECT("*")
                .FROM("g_role")
                .WHERE("id=#{id}");
        return selectById.toString();
    }
    /**
     * 通过roleName查询id
     */
    public String selectIdByRoleName(String roleName){
        SQL selectIdByUserNum = new SQL()
                .SELECT("id")
                .FROM("g_role")
                .WHERE("role_name=#{roleName}");
        return selectIdByUserNum.toString();
    }
}
