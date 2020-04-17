package com.hekiraku.gemini.domain.entity;

import com.hekiraku.gemini.domain.base.BaseEntity;
import lombok.*;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/4
 * 功能说明：
 */
@Setter
@Getter
@Builder
@ToString(callSuper = true)
public class TextUserEntity extends BaseEntity {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 文章id
     */
    private Long textId;
    /**
     * 人格
     */
    private String soulChar;
}
