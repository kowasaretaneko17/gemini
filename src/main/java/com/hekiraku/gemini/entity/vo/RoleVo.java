package com.hekiraku.gemini.entity.vo;

import com.hekiraku.gemini.entity.base.PaginationEntity;
import lombok.Builder;
import lombok.Data;

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
@Data
@Builder
public class RoleVo extends PaginationEntity implements Serializable {
    private String roleName;
    private List<ResourceVo> resources;
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
