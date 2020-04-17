package com.hekiraku.gemini.domain.base;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/3/22
 * 功能说明：基础字段信息类
 */
@Data
@NoArgsConstructor
public class BaseEntity implements Serializable {
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
