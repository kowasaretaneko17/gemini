package com.hekiraku.gemini.aop.shiro;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;

import static com.hekiraku.gemini.common.enums.AuthResultEnums.AUTH_ANONYMOUS;
import static com.hekiraku.gemini.common.enums.AuthResultEnums.AUTH_ROLE;
import static com.hekiraku.gemini.common.enums.OperatingResultEnums.S_NOT_FOUND;

/**
 * controller层 shiro异常处理类
 * Created by Administrator on 2018/10/13.
 */
@ControllerAdvice
public class ShiroExceptionHandler {

    /**
     * 未认证异常处理
     * @return
     */
    @ResponseBody
    @ExceptionHandler(UnauthenticatedException.class)
    public Map<String, Object> authenticationException() {
        return AUTH_ANONYMOUS.result();
    }

    /**
     * 未授权异常处理
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = UnauthorizedException.class)
    public Map<String, Object> authorizationException() {
        return AUTH_ROLE.result();
    }

    /**
     * 捕捉404异常
     * @return
     */
    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    public Map<String, Object> handle404(NoHandlerFoundException e) {
        return S_NOT_FOUND.result();
    }


}
