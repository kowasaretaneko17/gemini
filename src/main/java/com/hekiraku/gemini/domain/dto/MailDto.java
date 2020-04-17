/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.domain.dto;

import com.hekiraku.gemini.domain.base.SerializableEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 邮件发送Dto
 *
 * @author weiyimeng<weiyimeng @ bytedance.com>
 * @date 03/24/2020 4:08 下午
 */
@Setter
@Getter
public class MailDto extends SerializableEntity {
    @ApiModelProperty(required = true,notes = "收件人",example = "1510381250@qq.com")
    String mailTarget;
    @ApiModelProperty(required = true,notes = "收件人昵称",example = "花鸽子")
    String mailTargetNickName;
    @ApiModelProperty(required = true,notes = "发件人",example = "gemini_rule001@163.com")
    String mailSource;
    @ApiModelProperty(required = true,notes = "发件人昵称",example = "碧落君")
    String mailSourceNickName;
    @ApiModelProperty(required = true,notes = "邮箱类型",example = "SMTP")
    String mailType;
    @ApiModelProperty(required = true,notes = "主题",example = "gemini验证码")
    String subject;
    @ApiModelProperty(required = true,notes = "正文",example = "123456")
    String content;
    @ApiModelProperty(required = true,notes = "发送时间",example = "2020-03-25")
    Date sentDate;
}
