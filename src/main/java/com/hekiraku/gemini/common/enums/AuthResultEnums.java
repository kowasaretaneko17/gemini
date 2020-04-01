package com.hekiraku.gemini.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回码三位数
 * 业务状态码定义五位数。
 * 前两位表示业务类型 ，后三位表示具体业务逻辑
 * 前两位：00，系统 10，业务，80，鉴权
 */
public enum AuthResultEnums {
    /**---鉴权返回码80000-80999---*/
    AUTH_LOGIN("80000","登录失败"),
    AUTH_LOGIN_PARAM("80001","用户名或密码错误"),
    AUTH_ANONYMOUS("80002","无权访问，当前是匿名访问，请先登录"),
    AUTH_ROLE("80003","无权访问，当前账号权限不足"),
    AUTH_TOKEN("80004","token检验失败"),
    AUTH_KAPTCHA("80005","生成验证码失败"),
    AUTH_USERINFO("80006","获取用户信息失败"),
    AUTH_REGISTER("80007","注册失败"),
    AUTH_USERNAME("80008","已存在用户名"),
    AUTH_MAIL("80009","已存在邮箱"),
    AUTH_PHONE("80010","已存在手机号");
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
    public Map<String, Object> result() {
        Map result = new HashMap<String, Object>(3);
        result.put("code", this.code);
        result.put("msg", this.desc);
        return result;
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
