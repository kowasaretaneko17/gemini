package com.hekiraku.gemini.common.enums;

/**
 * 通用返回码三位数
 * 业务状态码定义五位数。
 * 异常信息为负数。
 * 前两位表示业务类型 ，后三位表示具体业务逻辑
 * 前两位：00，系统 10，业务，80，鉴权
 */
public enum ExceptionResultEnums {
    /**---通用返回码---*/
    E_LOGIN("-9999","登录异常"),
    E_UPDATE_NOTE("-9998","更新日记异常"),
    E_QUERY_NOTE("-9997","获取日记异常");
    /**---通用返回码---*/
    private String code;
    private String desc;

    ExceptionResultEnums(String code,String desc){

        this.code = code;
        this.desc = desc;
    }

    public static ExceptionResultEnums getCodeType(String code){

        for(ExceptionResultEnums codeDef: values()){
            if(codeDef.getCode().equals(code)){
                return codeDef ;
            }
        }
        throw new AssertionError("未知的code，code：" + code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
