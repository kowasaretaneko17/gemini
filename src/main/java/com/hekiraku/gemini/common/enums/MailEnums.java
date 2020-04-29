package com.hekiraku.gemini.common.enums;

/**
 * 邮箱配置
 */
public enum MailEnums {
    /**---邮箱+授权码---*/
    /**碧落君*/
    M_HEKIRAKU_SOURCE("1239407570@qq.com","knbvunquuxemggfe"),
    /**gemini官方163邮箱*/
    M_GEMINI_SOURCE("gemini_rule001@163.com","RFBVIIUCUASGKNXG"),

    /**---邮箱+授权码---*/


    /**---邮箱+昵称---*/
    /**碧落君（昵称配置）*/
    M_HEKIRAKU_TARGET_INFO("1239407570@qq.com","hekiraku"),
    /**花鸽子（昵称配置）*/
    M_WANLLY_TARGET_INFO("1510381250@qq.com","wanlly"),
    /**---邮箱+昵称---*/

    /**---场合+主题标语---*/
    M_REGISTER_SUBJECT("注册","邮箱验证码"),
    /**---场合+主题标语---*/

    /**---协议+编码---*/
    M_SMTP_UTF8("STMP","UTF-8");
    /**---协议+编码---*/
    private String code;
    private String desc;

    MailEnums(String code, String desc){

        this.code = code;
        this.desc = desc;
    }

    public static MailEnums getCodeType(String code){

        for(MailEnums codeDef: values()){
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
