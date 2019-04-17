package com.hekiraku.gemini.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/3/22
 * 功能说明：
 */
@Data
@Builder
public class UserInfoVo implements Serializable {
    private String userName;
    private String userNum;
    @JsonIgnore
    private String password;
    private String phone;
    private String email;
    private String nickName;
    private String roleName;
}
