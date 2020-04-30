package com.hekiraku.gemini.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hekiraku.gemini.domain.base.SerializableEntity;
import lombok.*;

import java.io.Serializable;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/4/19
 * 功能说明：
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ResourceVo extends SerializableEntity {
    /**
     * 资源id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long resourceId;
    /**
     * 资源编码
     */
    private String resourceCode;
    /**
     * 资源名称
     */
    private String resourceName;
}
