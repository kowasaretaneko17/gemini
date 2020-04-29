package com.hekiraku.gemini.domain.entity;

import com.hekiraku.gemini.domain.base.BaseEntity;
import lombok.*;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
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
