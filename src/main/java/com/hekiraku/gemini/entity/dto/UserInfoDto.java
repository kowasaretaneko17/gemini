package com.hekiraku.gemini.entity.dto;

import com.hekiraku.gemini.entity.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/3/22
 * 功能说明：
 */
@Data
public class UserInfoDto {
    @NotNull
    private String userName;
    @NotNull
    private String password;
}
