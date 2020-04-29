package com.hekiraku.gemini.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hekiraku.gemini.domain.base.SerializableEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/3/22
 * 功能说明：
 */

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@ApiModel(value = "UserInfoVo",description = "用户返回信息")
public class UserInfoVo extends SerializableEntity {
    @ApiModelProperty(notes = "账户",example = "zhangsan")
    private String identityCode;
    @ApiModelProperty(notes = "用户编码",example = "448170509163560960")
    private Long userId;
    @JsonIgnore
    @ApiModelProperty(notes = "密码",example = "12345",hidden = true)
    private String password;
    @ApiModelProperty(notes = "手机号",example = "1322232313")
    private String phone;
    @ApiModelProperty(notes = "邮箱地址", example = "gemini_rule001@163.com")
    private String email;
    @ApiModelProperty(notes = "昵称",example = "碧落君(不)")
    private String nickName;
    @ApiModelProperty(notes = "是否冻结(0正常；1冻结)",example = "0")
    private Integer lock;
    @JsonIgnore
    private List<RoleVo> roles;
    @ApiModelProperty(name = "setRoles",notes = "角色信息",dataType = "Set<RoleVo>")
    public Set<RoleVo> getSetRoles(){
        return roles.stream().collect(Collectors.toSet());
    }
    @ApiModelProperty(name = "SetResources",notes = "资源信息",dataType = "Set<ResourceVo>")
    public Set<ResourceVo> getSetResources(){
        if(CollectionUtils.isEmpty(roles)){
            return new HashSet<ResourceVo>();
        }
        Set<ResourceVo> resourceVos = new HashSet<>();
        for(RoleVo roleVo :roles){
            resourceVos.addAll(roleVo.getSetResources());
        }
        return resourceVos;
    }
}
