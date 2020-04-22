package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.domain.entity.ResourceEntity;
import com.hekiraku.gemini.domain.vo.ResourceVo;

import java.util.List;

/**
 * 构建组：
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/22
 * 功能说明：
 */
public interface ResourceMapper {
    /**
     * 根据resouceId获取资源信息
     * @param resourceId
     * @return
     */
    ResourceVo selectByResourceId(Long resourceId);

    /**
     * 新建/更新
     * @param resourceEntity
     * @return
     */
    int createOrUpdateResource(ResourceEntity resourceEntity);

    /**
     * 根据roleId获取资源信息
     */

    List<ResourceVo> selectByRoleId(Long roleId);
}
