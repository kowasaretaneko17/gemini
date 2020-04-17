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
 * 文章简述
 *
 * @author bytedance<bytedance @ bytedance.com>
 * @task
 * @date 04/17/2020 6:24 下午
 */
public class TextSummaryDynaSqlProvider {

    public String selectTextSummaryByTextId(Long textId){
        SQL selectTextSummaryByTextId = new SQL()
                .SELECT("*")
                .FROM("g_text_summary")
                .WHERE("text_id=#{textId}");
        return selectTextSummaryByTextId.toString();
    }

}
