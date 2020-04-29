/**
 * *****************************************************
 * Copyright (C) 2020 geminiif.com.cn. All Rights Reserved
 * This file is part of gemini center tech project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.domain.base;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author hekiraku<hekiraku @ geminiif.com.cn>
 * @task 简单基础信息类
 * @date 04/17/2020
 */
@Getter
@Setter
@NoArgsConstructor
public class SimpleBaseEntity extends SerializableEntity {
    /**主键*/
    protected Long id;
}
