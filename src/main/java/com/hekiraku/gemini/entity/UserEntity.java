package com.hekiraku.gemini.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/3/22
 * 功能说明：
 */
@Data
@Builder
@ToString(callSuper=true)
public class UserEntity extends BaseEntity{
    private String userName;
    private String userNum;
    private String phone;
    private String email;
    private String password;
    private String nickName;
}
