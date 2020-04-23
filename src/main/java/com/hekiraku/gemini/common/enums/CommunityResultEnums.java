package com.hekiraku.gemini.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回码三位数
 * 业务状态码定义五位数。
 * 前两位表示业务类型 ，后三位表示具体业务逻辑
 * 前两位：00，系统 10，业务，80，鉴权
 */
public enum CommunityResultEnums {
    /**
     * ---社区业务返回码11000-11999---
     */
    C_TEXT_OPEN("11001","获取当天开放日记失败");

    /**
     * ---社区业务返回码11000-11999---
     */

    private String code;

    private String desc;

    CommunityResultEnums(String code, String desc) {

        this.code = code;
        this.desc = desc;
    }

    public static CommunityResultEnums getCodeType(String code) {

        for (CommunityResultEnums codeDef : values()) {
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
