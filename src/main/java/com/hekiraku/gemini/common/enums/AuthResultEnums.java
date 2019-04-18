package com.hekiraku.gemini.common.enums;

/**
 * 通用返回码三位数
 * 业务状态码定义五位数。
 * 前两位表示业务类型 ，后三位表示具体业务逻辑
 * 前两位：00，系统 10，业务，80，鉴权
 */
public enum AuthResultEnums {
    /**---鉴权返回码80000-80999---*/
    AUTH_LOGIN("80001","用户名或密码错误"),
    AUTH_ROLE("80002","权限错误");
    /**---鉴权返回码80000-80999---*/

    private String code;
    private String desc;

    AuthResultEnums(String code,String desc){

        this.code = code;
        this.desc = desc;
    }

    public static AuthResultEnums getCodeType(String code){

        for(AuthResultEnums codeDef: values()){
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
