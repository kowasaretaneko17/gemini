package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.domain.entity.RoleEntity;
import com.hekiraku.gemini.domain.vo.RoleVo;

import java.util.List;

/**
 * 构建组：
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/1/23
 * 功能说明：
 */
public interface RoleMapper {
    List<RoleVo> selectByUserId(Long userId);
    RoleVo selectByRoleCode(String roleCode);
    int createOrUpdateRole(RoleEntity roleEntity);
}
