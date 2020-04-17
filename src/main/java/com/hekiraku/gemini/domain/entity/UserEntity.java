package com.hekiraku.gemini.domain.entity;

import com.hekiraku.gemini.domain.base.BaseEntity;
import lombok.*;

/**
 * 构建组：游荡的野指针
 * 作者:weiyimeng
 * 邮箱:weiyimeng@bytedance.com
 * 日期:2019/3/22
 * 功能说明：
 */
@Setter
@Getter
@Builder
@ToString(callSuper=true)
public class UserEntity extends BaseEntity {
    /**
     * 账号
     */
    private String identityCode;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 是否冻结
     */
    private Integer lock;
}
