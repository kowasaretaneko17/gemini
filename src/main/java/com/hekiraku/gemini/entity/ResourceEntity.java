package com.hekiraku.gemini.entity;

import com.hekiraku.gemini.entity.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/19
 * 功能说明：
 */
@Data
@Builder
@ToString(callSuper=true)
public class ResourceEntity extends BaseEntity {
    private String resourceName;
}
