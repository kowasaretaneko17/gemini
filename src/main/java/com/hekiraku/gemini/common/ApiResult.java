package com.hekiraku.gemini.common;
import lombok.Data;

import java.io.Serializable;
import static com.hekiraku.gemini.common.enums.NormalResultEnums.N_SUCCESS;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/22
 * 功能说明：
 */
@Data
public class ApiResult<T> implements Serializable {

    /**
     * 成功状态
     */
    private Boolean success;

    /**
     * 状态代码
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据对象
     */
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

