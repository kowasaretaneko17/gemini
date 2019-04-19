package com.hekiraku.gemini.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回码三位数
 * 业务状态码定义五位数。
 * 前两位表示业务类型 ，后三位表示具体业务逻辑
 * 前两位：00，系统 10，业务，80，鉴权
 */
public enum OperatingResultEnums {
    /**
     * ---业务返回码10000-10999---
     */
    S_NOT_FOUND("10001", "服务器未找到资源"),
    S_ERROR("10002", "服务器错误");

    /**
     * ---业务返回码10000-10999---
     */

    private String code;

    private String desc;

    OperatingResultEnums(String code, String desc) {

        this.code = code;
        this.desc = desc;
    }

    public static OperatingResultEnums getCodeType(String code) {

        for (OperatingResultEnums codeDef : values()) {
            if (codeDef.getCode().equals(code)) {
                return codeDef;
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
