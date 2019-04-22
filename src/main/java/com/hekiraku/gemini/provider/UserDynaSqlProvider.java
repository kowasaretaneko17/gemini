package com.hekiraku.gemini.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/19
 * 功能说明：
 */
public class UserDynaSqlProvider {
    /**
     * 通过id查询user表信息
     */
    public String selectById(String id){
        SQL selectById = new SQL()
                .SELECT("*")
                .FROM("g_user")
                .WHERE("id=#{id}");
        return selectById.toString();
    }

    /**
     * 通过username查询user表
     * @param userName
     * @return
     */
    public String selectByUserName(String userName){
        SQL selectIdByUserName = new SQL()
                .SELECT("id")
                .FROM("g_user")
                .WHERE("user_name=#{userName}");
        SQL selectAllById = new SQL()
                .SELECT("*")
                .FROM("g_user")
                .WHERE("id=("+selectIdByUserName.getSelf()+")");
        return selectAllById.toString();
    }
    /**
     * 通过userName查询所有信息
     * @param userName
     * @return
     */
    public String selectAllByUserName(String userName) {
        SQL selectAllByUserName = new SQL()
                .SELECT("*")
                .FROM("g_user gu")
                .LEFT_OUTER_JOIN("g_user_role gur on gu.id = gur.user_id")
                .LEFT_OUTER_JOIN("g_role gr on gr.id=gur.role_id")
                .LEFT_OUTER_JOIN("g_res_role grr on gr.id = grr.role_id")
                .LEFT_OUTER_JOIN("g_resource gre on gre.id = grr.res_id")
                .WHERE("gu.user_name=#{userName}");
        return selectAllByUserName.toString();
    }
}
