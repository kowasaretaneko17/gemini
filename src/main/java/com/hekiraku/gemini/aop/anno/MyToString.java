/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.aop.anno;

/**
 * @author bytedance<bytedance @ bytedance.com>
 * @task lombok自带的tostring太坑爹了，自己写一个
 * @date 04/16/2020 3:26 下午
 */

public @interface MyToString {
    boolean callSuper() default false;
}