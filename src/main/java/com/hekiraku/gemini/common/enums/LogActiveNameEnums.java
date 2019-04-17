package com.hekiraku.gemini.common.enums;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/3
 * 功能说明：
 */
public enum LogActiveNameEnums {
    /**用户登录*/
    LOG_LOGIN("001","用户登录"),
    /**用户登出*/
    LOG_LOGOUT("002","用户登出");
    private String name;
    private String desc;

    LogActiveNameEnums(String name,String desc){

        this.name = name;
        this.desc = desc;
    }

    public static LogActiveNameEnums getCodeType(String name){

        for( LogActiveNameEnums codeDef: values()){
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

