/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.domain.vo;

import lombok.*;

/**
 * @author bytedance<bytedance @ bytedance.com>
 * @task
 * @date 04/17/2020 6:37 下午
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TextDetailVo {
    /**
     * 文章内容
     */
    private String textDetail;
}
