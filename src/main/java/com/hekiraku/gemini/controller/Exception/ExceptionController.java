package com.hekiraku.gemini.controller.Exception;

import com.hekiraku.gemini.common.ApiResult;
import org.apache.shiro.authc.AccountException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/23
 * 功能说明：
 */
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(AccountException.class)
    public ApiResult handleShiroException(Exception ex) {
        return ApiResult.buildFail("-9999",ex.getMessage());
    }
}
