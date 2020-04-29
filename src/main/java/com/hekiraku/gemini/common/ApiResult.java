package com.hekiraku.gemini.common;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import static com.hekiraku.gemini.common.enums.NormalResultEnums.N_SUCCESS;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/1/22
 * 功能说明：
 */
@Data
@ApiModel(value = "ApiResult<T>",description = "API接口通用返回对象")
public class ApiResult<T> implements Serializable {

    /**
     * 成功状态
     */
    @ApiModelProperty(notes = "成功状态",allowableValues = "true,false")
    private Boolean success;

    /**
     * 状态码
     */
    @ApiModelProperty(notes = "状态码")
    private String code;

    /**
     * 返回消息
     */
    @ApiModelProperty(notes = "返回消息")
    @JsonProperty("msg")
    private String message;

    /**
     * 返回数据对象
     */
    @ApiModelProperty(notes = "返回对象",dataType = "T")
    @JsonProperty("result")
    private T data;

    public ApiResult() {
        super();
    }

    public ApiResult(Boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }

    /**
     * 构建成功对象，拼装一个正常的返回
     */
    public static <T> ApiResult<T> buildSuccessNormal(String msg, T data) {
        ApiResult<T> apiResultDto = new ApiResult<T>();
        apiResultDto.setSuccess(Boolean.TRUE);
        apiResultDto.setMessage(msg);
        apiResultDto.setCode(N_SUCCESS.getCode());
        apiResultDto.setData(data);
        return apiResultDto;
    }

    /**
     * 构建成功对象，拼装一个正常的返回
     */
    public static ApiResult successMsg(String msg) {
        ApiResult apiResultDto = new ApiResult();
        apiResultDto.setSuccess(Boolean.TRUE);
        apiResultDto.setCode(N_SUCCESS.getCode());
        apiResultDto.setMessage(msg);
        return apiResultDto;
    }
    public static ApiResult buildFail(String code, String message) {
        ApiResult apiResultDto = new ApiResult();
        apiResultDto.setSuccess(Boolean.FALSE);
        apiResultDto.setCode(code);
        apiResultDto.setMessage(message);
        return apiResultDto;
    }
    public static <T> ApiResult<T> buildFailNormal(String code, String message,T data) {
        ApiResult apiResultDto = new ApiResult();
        apiResultDto.setSuccess(Boolean.FALSE);
        apiResultDto.setCode(code);
        apiResultDto.setMessage(message);
        apiResultDto.setData(data);
        return apiResultDto;
    }
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ApiResult{");
        sb.append("success=").append(success);
        sb.append(", code='").append(code).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}

