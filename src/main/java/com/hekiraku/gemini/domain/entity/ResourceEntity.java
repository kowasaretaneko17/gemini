package com.hekiraku.gemini.domain.entity;

import com.hekiraku.gemini.domain.base.BaseEntity;
import lombok.*;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
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
