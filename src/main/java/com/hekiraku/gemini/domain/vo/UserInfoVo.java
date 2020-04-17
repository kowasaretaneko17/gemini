package com.hekiraku.gemini.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * 日期:2019/3/22
 * 功能说明：
 */

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@ApiModel(value = "UserInfoVo",description = "用户返回信息")
public class UserInfoVo implements Serializable {
    @JsonIgnore
    private String id;
    @ApiModelProperty(notes = "用户名",example = "zhangsan")
    private String userName;
    @ApiModelProperty(notes = "用户编号",example = "201904031010001")
    private String userNum;
    @JsonIgnore
    @ApiModelProperty(notes = "密码",example = "12345",hidden = true)
    private String password;
    @ApiModelProperty(notes = "手机号",example = "1322232313")
    private String phone;
    @ApiModelProperty(notes = "邮箱地址", example = "gemini@163.com")
    private String email;
    @ApiModelProperty(notes = "昵称",example = "碧落君(不)")
    private String nickName;
    @ApiModelProperty(notes = "是否冻结(0正常；1冻结)",example = "0")
    private String lock;
    @JsonIgnore
    private List<RoleVo> roles;
    @ApiModelProperty(name = "setRoles",notes = "角色信息",dataType = "Set<String>")
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
    @ApiModelProperty(name = "SetResources",notes = "资源信息",dataType = "Set<String>")
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
