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
