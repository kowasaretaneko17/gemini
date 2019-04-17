package com.hekiraku.gemini.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/23
 * 功能说明：
 */
@Data
@Builder
@ToString(callSuper=true)
public class RoleEntity extends BaseEntity{
    private String roleName;
}
