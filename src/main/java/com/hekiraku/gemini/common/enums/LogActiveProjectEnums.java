package com.hekiraku.gemini.common.enums;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/3
 * 功能说明：
 */
public enum LogActiveProjectEnums {
    /**gemini操作日志*/
    GEMINI("GEMINI","后台系统");
    private String name;
    private String desc;

    LogActiveProjectEnums(String name,String desc){

        this.name = name;
        this.desc = desc;
    }

    public static LogActiveProjectEnums getCodeType(String name){

        for( LogActiveProjectEnums codeDef: values()){
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
