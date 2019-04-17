package com.hekiraku.gemini.common.enums;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/3
 * 功能说明：
 */
public enum LogActiveTypeEnums {
    /**系统日志*/
    SYSTEM("SYS","系统日志"),
    /**业务动作*/
    ACTIVE("ACT","用户动作");
    private String name ;
    private String desc;

    LogActiveTypeEnums(String name,String desc){

        this.name = name;
        this.desc = desc;
    }

    public static LogActiveTypeEnums getCodeType(String name){

        for( LogActiveTypeEnums codeDef: values()){
            if(codeDef.getName().equals(name)){
                return codeDef ;
            }
        }
        throw new AssertionError("未知的类型，activeName：" + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
