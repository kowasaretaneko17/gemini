package com.hekiraku.gemini.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/3
 * 功能说明：
 */
public class ActiveLogDynaSqlProvider {
    /**
     * 如果参数是@param注解的，必须使用map接收参数
     * @param para
     * @return
     */
    public String  selectAllByActiveName(Map<String, Object> para){
        SQL selectAllByActiveName = new SQL()
                .SELECT("*")
                .FROM("g_activity_log")
                .WHERE("active_name="+para.get("activeName"));
        return selectAllByActiveName.toString();
    }
    public String create(){
        SQL create = new SQL()
                .INSERT_INTO("g_activity_log")
                .VALUES("buss_id", "#{bussId}")
                .VALUES("active_project", "#{activeProject}")
                .VALUES("active_type", "#{activeType}")
                .VALUES("active_name", "#{activeName}")
                .VALUES("active_method", "#{activeMethod}")
                .VALUES("active_data", "#{activeData}")
                .VALUES("active_desc", "#{activeDesc}")
                .VALUES("create_user_id", "#{createUserId}")
                .VALUES("create_time", "#{createTime}");
        return create.toString();
    }
}
