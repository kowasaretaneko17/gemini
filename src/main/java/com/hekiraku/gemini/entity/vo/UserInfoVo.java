package com.hekiraku.gemini.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * 日期:2019/3/22
 * 功能说明：
 */
@Data
@Builder
public class UserInfoVo extends PaginationEntity implements Serializable {
    private String userName;
    private String userNum;
    @JsonIgnore
    private String password;
    private String phone;
    private String email;
    private String nickName;
    private String lock;
    private List<RoleVo> roles;
    public Set<String> getSetRoles(){
        Set<String> setRoles = new HashSet<>();
        if(roles.isEmpty()||roles==null){
            return setRoles;
        }
        Iterator<RoleVo> listRoles = roles.iterator();
        while(listRoles.hasNext()){
            RoleVo roleVo = listRoles.next();
            setRoles.add(roleVo.getRoleName());
        }
        return setRoles;
    }
    public Set<String> getSetResources(){
        Set<String> setResources = new HashSet<>();
        if(roles.isEmpty()||roles==null){
            return setResources;
        }
        Iterator<RoleVo> listRoles = roles.iterator();
        while(listRoles.hasNext()){
            RoleVo roleVo = listRoles.next();
            setResources.addAll(roleVo.getSetResources());
        }
        return setResources;
    }
}
