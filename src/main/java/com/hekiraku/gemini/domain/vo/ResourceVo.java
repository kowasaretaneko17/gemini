package com.hekiraku.gemini.domain.vo;

import com.hekiraku.gemini.domain.base.SerializableEntity;
import lombok.*;

import java.io.Serializable;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
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
