/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.domain.base;

import java.io.Serializable;

/**
 * @author bytedance<bytedance @ bytedance.com>
 * @task 序列化基础类
 * @date 04/17/2020
 * idea设置自动生成序列化id：
 * Setting -> Inspections -> java ->Serialization issues全部勾选保存。
 * 然后对着类名直接alt+enter就有了
 */
public class SerializableEntity implements Serializable {
    protected static final long serialVersionUID = -2899657261548898680L;
}
