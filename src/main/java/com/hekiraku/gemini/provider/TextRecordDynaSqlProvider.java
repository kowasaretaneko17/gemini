package com.hekiraku.gemini.provider;

import com.hekiraku.gemini.entity.TextRecordEntity;
import org.apache.ibatis.jdbc.SQL;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/3
 * 功能说明：
 */
public class TextRecordDynaSqlProvider {
    /**
     * 通过用户，人格，和创造日期查找文章内容
     *
     * @param
     * @return
     */
    public String selectTextByDayUsrChar() {
        SQL selectTextByDayUsrChar = new SQL()
                .SELECT("*")
                .FROM("g_record")
                .WHERE("create_day = #{createDay} and user_num = #{userNum} and soul_char = #{soulChar}");
        return selectTextByDayUsrChar.toString();
    }

    /**
     * 创建一条日记
     *
     * @return
     */
    public String create() {
        SQL create = new SQL().INSERT_INTO("g_record")
                .VALUES("user_num", "#{userNum}")
                .VALUES("text", "#{text}")
                .VALUES("soul_char", "#{soulChar}")
                .VALUES("create_day", "#{createDay}")
                .VALUES("delete_flag", "#{deleteFlag}")
                .VALUES("rev", "#{rev}").VALUES("update_user_id", "#{updateUserId}")
                .VALUES("create_user_id", "#{createUserId}").VALUES("update_time", "#{updateTime}")
                .VALUES("create_time", "#{createTime}");
        return create.toString();
    }

    /**
     * 更新一条日记
     *
     * @return
     */
    public String update(TextRecordEntity textRecordEntity) {
        SQL create = new SQL().UPDATE("g_record").SET("text = #{text}").SET("update_time = #{updateTime}")
                .SET("update_user_id = #{updateUserId}").SET("rev = #{rev}").WHERE("id = #{id}");
        return create.toString();
    }

    /**
     * 根据id软删除一条日记
     */
    public String deleteSoft(TextRecordEntity textRecordEntity) {
        SQL deleteSoft = new SQL().UPDATE("g_record").SET("delete_flag = 1").WHERE("id = #{id}");
        return deleteSoft.toString();
    }

    /**
     * 根据用户与年份获取日记记录
     */
    public String selectSoulDiaryByUserAndYear(String years,String userNum){
        SQL selectSoulDiaryByUserAndYear = new SQL()
                .SELECT("soul_char,DAY(create_time) as day,MONTH(create_time) as month,YEAR(create_time) as year")
                .FROM("g_record")
                .WHERE("user_num = #{userNum} and YEAR(create_time) = #{years}");
        return selectSoulDiaryByUserAndYear.toString();
    }
}
