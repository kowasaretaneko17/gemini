/**
 * *****************************************************
 * Copyright (C) 2020 geminiif.com.cn. All Rights Reserved
 * This file is part of gemini center tech project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.domain.dto;

import com.hekiraku.gemini.domain.base.SerializableEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hekiraku<hekiraku@foxmail.com>
 * @task 分页参数
 * @date 04/23/2020 11:19 上午
 */
@Setter
@Getter
public class PageParamsDto extends SerializableEntity {
    private int pageNum;
    private int pageSize;
    public PageParamsDto(){
        this.pageNum=1;
        this.pageSize=10;
    }
}
