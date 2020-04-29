package com.hekiraku.gemini.domain.base;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/3/22
 * 功能说明：基础字段信息类
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseEntity extends SerializableEntity {
    /**主键*/
    protected Long id;
    /**创建人id*/
    protected Long createUserId;
    /**更新人id*/
    protected Long updateUserId;
    /**记录创建时间*/
    protected Date createTime;
    /**记录更新时间*/
    protected Date updateTime;
    /**版本号*/
    protected Integer rev;
    /**删除标识（1：删除；0：有效数据）*/
    protected Integer deleteFlag;
}
