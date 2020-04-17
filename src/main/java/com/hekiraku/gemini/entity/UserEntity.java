package com.hekiraku.gemini.entity;

import com.hekiraku.gemini.entity.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 构建组：游荡的野指针
 * 作者:weiyimeng
 * 邮箱:weiyimeng@bytedance.com
 * 日期:2019/3/22
 * 功能说明：
 */
@Data
@Builder
@ToString(callSuper=true)
public class UserEntity extends BaseEntity {
    private String userName;
    private Long userId;
    private String phone;
    private String email;
    private String password;
    private String nickName;
    private String lock;
}
