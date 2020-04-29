package com.hekiraku.gemini.domain.entity;

import com.hekiraku.gemini.domain.base.BaseEntity;
import lombok.*;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
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
