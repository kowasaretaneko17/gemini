package com.hekiraku.gemini.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hekiraku.gemini.common.enums.LogActiveNameEnums;
import com.hekiraku.gemini.common.enums.LogActiveProjectEnums;
import com.hekiraku.gemini.common.enums.LogActiveTypeEnums;
import com.hekiraku.gemini.domain.base.PaginationEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/3
 * 功能说明：
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class ActiveLogEntity extends PaginationEntity implements Serializable {
    /**自增主键*/
    @JsonIgnore
    private Integer id;
    /**数据id*/
    @ApiModelProperty(notes = "数据id")
    private String bussId;
    /**项目名称*/
    @ApiModelProperty(notes = "项目名称")
    private String activeProject;
    /**项目调用类型*/
    @ApiModelProperty(notes = "项目调用类型")
    private String activeType;
    /**调用名称*/
    @ApiModelProperty(notes = "调用名称")
    private String activeName;
    /**调用方法名称*/
    @ApiModelProperty(notes = "调用方法名称")
    private String activeMethod;
    /**调用数据*/
    @ApiModelProperty(notes = "调用数据")
    private String activeData;
    /**调用描述*/
    @ApiModelProperty(notes = "调用描述")
    private String activeDesc;
    /**创建人id*/
    @ApiModelProperty(notes = "创建人id")
    private String createUserId;
    /**记录创建时间*/
    @ApiModelProperty(notes = "创建时间")
    private Date createTime;

    /**
     * builder模式，试验一下
     * @return
     */
    public static class Builder{
        private String activeProject;
        private String activeType;
        private String bussId;
        private String activeName;
        private String activeMethod;
        private String activeData;
        private String activeDesc;
        private String createUserId;
        private Date createTime;
        public Builder(LogActiveProjectEnums project, LogActiveTypeEnums type, String bussId, LogActiveNameEnums activeName,String activeDesc){
            this.activeType = type.getName();
            this.activeProject = project.getName();
            this.bussId = bussId;
            this.activeName = activeName.getName();
            this.activeDesc = activeDesc;
        }
        public Builder activeMethod(String val){
            activeMethod = val;
            return this;
        }
        public Builder activeData(String val){
            activeData = val;
            return this;
        }
        public Builder createUserId(String val){
            createUserId = val;
            return this;
        }
        public Builder createTime(Date val){
            createTime = val;
            return this;
        }
        public ActiveLogEntity build(){
            return new ActiveLogEntity(this);
        }
    }
    protected ActiveLogEntity(Builder builder){
        activeProject = builder.activeProject;
        activeType = builder.activeType;
        activeName = builder.activeName;
        bussId = builder.bussId;
        activeMethod = builder.activeMethod;
        activeData = builder.activeData;
        activeDesc = builder.activeDesc;
        createTime = builder.createTime;
        createUserId = builder.createUserId;
    }


}
