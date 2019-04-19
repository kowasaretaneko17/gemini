package com.hekiraku.gemini.entity.base;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/3/22
 * 功能说明：
 */
@Data
@NoArgsConstructor
public class BaseEntity implements Serializable {
    /**主键*/
    protected String id;
    /**创建人id*/
    protected String createUserId;
    /**更新人id*/
    protected String updateUserId;
    /**记录创建时间*/
    protected Date createTime;
    /**记录更新时间*/
    protected Date updateTime;
    /**版本号*/
    protected Integer rev = 0;
    /**删除标识（1：删除；0：有效数据）*/
    protected String deleteFlag = "0";
}
