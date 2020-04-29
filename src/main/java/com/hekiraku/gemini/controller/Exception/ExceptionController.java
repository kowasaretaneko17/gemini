package com.hekiraku.gemini.controller.Exception;

import com.hekiraku.gemini.common.ApiResult;
import org.apache.shiro.authc.AccountException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/1/23
 * 功能说明：全局异常处理
 */
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(AccountException.class)
    public ApiResult handleShiroException(Exception ex) {
        return ApiResult.buildFail("-9999",ex.getMessage());
    }
}
