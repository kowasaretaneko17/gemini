package com.hekiraku.gemini.domain.entity;

import com.hekiraku.gemini.domain.base.BaseEntity;
import lombok.*;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/23
 * 功能说明：
 */
@Setter
@Getter
@Builder
@ToString(callSuper=true)
public class RoleEntity extends BaseEntity {
    /**角色Id*/
    private Long roleId;
    /**角色code*/
    private String roleCode;
    /**角色名称*/
    private String roleName;
}
