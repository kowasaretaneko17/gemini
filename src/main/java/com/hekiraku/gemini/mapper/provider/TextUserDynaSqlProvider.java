package com.hekiraku.gemini.mapper.provider;

import com.hekiraku.gemini.domain.entity.TextUserEntity;
import org.apache.ibatis.jdbc.SQL;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/3
 * 功能说明：
 */
public class TextUserDynaSqlProvider {
    /**
     * 通过用户，人格，和创造日期查找文章内容
     *
     * @param
     * @return
     */
    public String selectTextByDayUsrChar() {
        SQL selectTextByDayUsrChar = new SQL()
                .SELECT("*")
                .FROM("g_text_user")
                .WHERE("date_format(from_unixtime(create_time),'%Y-%m-%d') = #{createDay} and user_id = #{userId} and soul_char = #{soulChar}");
        return selectTextByDayUsrChar.toString();
    }

    /**
     * 创建一条日记g_text_user
     *
     * @return
     */
    public String createOrUpdateTextUser() {
        SQL create = new SQL().INSERT_INTO("g_text_user")
                .VALUES("user_id", "#{userId}")
                .VALUES("text_id", "#{textId}")
                .VALUES("soul_char", "#{soulChar}")
                .VALUES("update_user_id", "#{updateUserId}")
                .VALUES("create_user_id", "#{createUserId}")
                .SET("ON DUPLICATE KEY UPDATE")
                .VALUES("delete_flag", "#{deleteFlag}")
                .VALUES("rev", "#{rev}")
                .VALUES("update_user_id", "#{updateUserId}")
                ;
        return create.toString();
    }

    /**
     * 根据用户与年份获取日记记录
     */
    public String selectSoulDiaryByUserAndYear(String years,String userNum){
        SQL selectSoulDiaryByUserAndYear = new SQL()
                .SELECT("soul_char,DAY(create_time) as day,MONTH(create_time) as month,YEAR(create_time) as year")
                .FROM("g_text_user")
                .WHERE("user_num = #{userNum} and YEAR(create_time) = #{years}");
        return selectSoulDiaryByUserAndYear.toString();
    }
}
