/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hekiraku.gemini.service.DataRecordService;
import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 按照bealoon值，拿到全年的写日记记录
 *
 * @author bytedance<bytedance @ bytedance.com>
 * @date 03/23/2020 11:55 上午
 */

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@ApiModel(value = "SoulCharDateVo",description = "返回全年的写日记记录")
public class SoulCharDateVo implements Serializable {
    //角色
    private String soulChar;
    //日
    private Integer day;
    //月
    private Integer month;
    //年
    private Integer year;
}
