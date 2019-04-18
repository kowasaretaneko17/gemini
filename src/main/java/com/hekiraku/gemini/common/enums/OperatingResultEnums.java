package com.hekiraku.gemini.common.enums;

/**
 * 通用返回码三位数
 * 业务状态码定义五位数。
 * 前两位表示业务类型 ，后三位表示具体业务逻辑
 * 前两位：00，系统 10，业务，80，鉴权
 */
public enum OperatingResultEnums {
    /**---业务返回码10000-10999---*/
    /**中间状态0100-0199*/
    DO_PENDING("0100","查询中"),
    /**失败1000-2000*/
    DO_FAIL("1000","查询失败") ,
    /**--查询数据状态不符合业务需求1100-1199*/
    DO_QUERY_EMPTY("1100","查询数据为空"),
    DO_QUERY_DELETE_EMPTY("1101","删除失败，找不到要删除的信息"),
    /**--缺少参数1200-1299*/
    DO_PARAM_LACK("1200","缺少参数"),
    DO_PARAM_LACK_QUERY_HOUSE_KEY("1201","参数错误，楼盘查询缺少关键字"),
    DO_PARAM_LACK_QUERY_HOUSE_CITY_CODE("1202","参数错误，城市国际编码不能为空"),
    DO_PARAM_LACK_QUERY_HOUSE_PROJECT_ID("1203","参数错误，楼盘编号不能为空"),
    DO_PARAM_LACK_QUERY_HOUSE_BUILDING_ID("1204","参数错误，楼栋编号不能为空"),
    DO_PARAM_LACK_APPLYNO("1205","参数错误，订单编号不能为空"),
    DO_PARAM_LACK_SIGN_MATERIAL_ID("1206","参数错误，资料签署编码不能为空"),
    DO_PARAM_LACK_SIGN_TYPE("1207","参数错误，页面标识不能为空"),
    DO_PARAM_LACK_SIGN_FILE_ID("1208","参数错误，文件id不能为空"),
    DO_PARAM_LACK_SIGN_FILE_LIST("1209","参数错误，资料信息不能为空"),

    /**--参数错误1300-1399*/
    DO_PARAM_ERROR("1300","参数错误"),
    DO_PARAM_ERROR_PRICE_SOURCE("1301","不存在的评估公司"),
    DO_PARAM_ERROR_SIGN_SUFFIX_ERROR("1302","文件后缀不合法，只接受doc或docx文件"),
    /**--状态错误1400-1499*/
    DO_STATE_ERROR_RISK_REFUSE("1401","状态错误，该订单为风险拒单"),
    DO_STATE_ERROR_NOT_FINISHED("1402","状态错误，该订单不是终止订单"),
    DO_STATE_ERROR_WAS_BALCK("1403","状态错误，该订单已经加入黑名单"),
    DO_PARAM_ERROR_SIGN_MATERIAL_ID_HAD("1404","已存在该签署资料编码"),
    /**--调用第三方接口失败1500-1599*/
    DO_DUBBO_UPLOAD_ERROR("1501","调用上传SDK上传失败"),
    /**异常状态-9000--9999*/
    DO_EXCEPTION("-9999","查询异常");
    /**---业务返回码10000-10999---*/

    private String code;
    private String desc;

    OperatingResultEnums(String code,String desc){

        this.code = code;
        this.desc = desc;
    }

    public static OperatingResultEnums getCodeType(String code){

        for(OperatingResultEnums codeDef: values()){
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
