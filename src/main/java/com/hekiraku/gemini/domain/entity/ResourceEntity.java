package com.hekiraku.gemini.domain.entity;

import com.hekiraku.gemini.domain.base.BaseEntity;
import lombok.*;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/4/19
 * 功能说明：
 */
@Builder
@Setter
@Getter
@ToString(callSuper=true)
public class ResourceEntity extends BaseEntity {
    /**
     * 资源id
     */
    private Long resId;
    /**
     * 资源code
     */
    private String resourceCode;
    /**
     * 资源名称
     */
    private String resourceName;
}
