/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * 文章详情表
 *
 * @author bytedance<bytedance @ bytedance.com>
 * @task
 * @date 04/17/2020 5:48 下午
 */
public class TextDetailDynaSqlProvider {
    /**
     * 通过textId获取文章详情
     * @param textId
     * @return
     */
    public String selectByTextId(Long textId){
        SQL selectByTextId = new SQL()
                .SELECT("*")
                .FROM("g_text_detail")
                .WHERE("text_id = #{textId}");
        return selectByTextId.toString();
    }

    /**
     * 插入文章内容
     */
    public String createOrUpdateTextDetail(Long textId){
        SQL createOrUpdateTextDetail = new SQL().INSERT_INTO("g_text_detail")
                .VALUES("text_id", "#{textId}")
                .VALUES("text_detail", "#{textDetail}")
                .SET("ON DUPLICATE KEY UPDATE")
                .VALUES("text_detail", "#{textDetail}");
        return createOrUpdateTextDetail.toString();
    }

}
