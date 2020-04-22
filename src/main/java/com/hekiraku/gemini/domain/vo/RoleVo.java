package com.hekiraku.gemini.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hekiraku.gemini.domain.base.SerializableEntity;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/19
 * 功能说明：
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RoleVo extends SerializableEntity {
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 角色名称
     */
    private String roleName;
    @JsonIgnore
    private List<ResourceVo> resources;
    @JsonIgnore
    public Set<ResourceVo> getSetResources(){
        return resources.stream().collect(Collectors.toSet());
    }
}
