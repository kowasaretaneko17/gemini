package com.hekiraku.gemini.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hekiraku.gemini.entity.base.PaginationEntity;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
public class RoleVo implements Serializable {
    private String roleName;
    @JsonIgnore
    private List<ResourceVo> resources;
    @JsonIgnore
    public Set<String> getSetResources(){
        Set<String> setRoles = new HashSet<>();
        if(resources.isEmpty()||resources==null){
            return setRoles;
        }
        Iterator<ResourceVo> listRoles = resources.iterator();
        while(listRoles.hasNext()){
            ResourceVo roleVo = listRoles.next();
            setRoles.add(roleVo.getResourceName());
        }
        return setRoles;
    }
}
