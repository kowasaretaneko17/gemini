/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

/**
 * 自定义处理后的内容
 *
 * @author bytedance<bytedance @ bytedance.com>
 * @date 03/23/2020 6:13 下午
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@ApiModel(value = "SoulCharVo",description = "返回全年的写日记记录")
public class SoulCharVo implements Serializable {
    private Boolean ura;
    private Boolean omote;
    public SoulCharVo(){
        this.ura = false;
        this.omote = false;

    }
}
