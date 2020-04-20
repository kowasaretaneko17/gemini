/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.mapper;
import com.hekiraku.gemini.domain.entity.TextSummaryEntity;
import com.hekiraku.gemini.domain.vo.TextDetailVo;
import com.hekiraku.gemini.domain.vo.TextSummaryVo;
import com.hekiraku.gemini.mapper.provider.TextSummaryDynaSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 文章简述表
 *
 * @author bytedance<bytedance @ bytedance.com>
 * @task
 * @date 04/17/2020 5:57 下午
 */
public interface TextSummaryMapper {
    /**
     * 通过textId获取文章简述内容
     * @param textId
     * @return
     */
    @SelectProvider(type = TextSummaryDynaSqlProvider.class,method = "selectTextSummaryByTextId")
    public TextSummaryEntity selectTextSummaryByTextId(Long textId);

    /**
     * 通过textId获取文章简述+详细内容
     * @param textId
     * @return
     */
    @SelectProvider(type = TextSummaryDynaSqlProvider.class,method = "selectTextSummaryByTextId")
    @Results(id = "textDetail",value = {
            @Result(property = "textDetail",column = "textId",javaType = TextDetailVo.class,one = @One(select = "com.hekiraku.gemini.mapper.TextDetailMapper.selectByTextId"))
    })
    public TextSummaryVo selectTextByTextId(Long textId);

    /**
     * 插入或更新一条信息
     * @param textSummaryEntity
     * @return
     */
    @InsertProvider(type = TextSummaryDynaSqlProvider.class,method = "createOrUpdateTextSummary")
    public int createOrUpdateTextSummary(TextSummaryEntity textSummaryEntity);

}
