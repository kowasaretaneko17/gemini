/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.domain.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author weiyimeng<weiyimeng @ bytedance.com>
 * @task 简单基础信息类
 * @date 04/17/2020
 */
@Data
@NoArgsConstructor
public class SimpleBaseEntity implements Serializable {
    /**主键*/
    protected Long id;
}
