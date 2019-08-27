package com.hekiraku.gemini.aop.jwt;

import com.hekiraku.gemini.aop.threadLocal.SessionLocal;
import com.hekiraku.gemini.common.constants.CommonConstant;
import com.hekiraku.gemini.entity.vo.UserInfoVo;
import com.hekiraku.gemini.mapper.UserMapper;
import com.hekiraku.gemini.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.hekiraku.gemini.common.enums.AuthResultEnums.AUTH_ANONYMOUS;

/**
 * Created by Administrator on 2019/1/6.
 */
@Slf4j
@Component
public class JWTFilter extends BasicHttpAuthenticationFilter {

    @Value("${jwt.anonymous.urls}")
    private String anonymousStr;

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;


    //每次获取url请求的时候，都会进的一个拦截验证
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String contextPath = WebUtils.getPathWithinApplication(WebUtils.toHttp(servletRequest));
        if (!StringUtils.isEmpty(anonymousStr)) {
            String[] anonUrls = anonymousStr.split(",");
            //匿名可访问的url,配置在yml文件中。
            for (int i = 0; i < anonUrls.length; i++) {
                if (contextPath.contains(anonUrls[i])) {
                    return true;
                }
            }
        }
        //获取请求头token，鉴权是否已经登录
        AuthenticationToken token = this.createToken(servletRequest, servletResponse);
        if (token.getPrincipal() == null) {
            handler401(servletResponse, AUTH_ANONYMOUS.getCode(), AUTH_ANONYMOUS.getDesc());
            return false;
        } else {
            try {
                this.getSubject(servletRequest, servletResponse).login(token);
                //如果tolen有效，那么去获取缓存中的userInfo信息
                String userNum = JWTUtil.getUserNum(token.getPrincipal().toString());
                String key = CommonConstant.USER_SIMPLE_INFO + userNum;
                UserInfoVo userInfoVo = (UserInfoVo) redisTemplate.opsForValue().get(key);
                SessionLocal.setUserInfo(userInfoVo);
                return true;
            } catch (Exception e) {
                String msg = e.getMessage();
                //token错误
                if (msg.contains("incorrect")) {
                    handler401(servletResponse, AUTH_ANONYMOUS.getCode(), msg);
                    return false;
                    //token过期
                } else if (msg.contains("expired")) {
                    //尝试刷新token
                    if (this.refreshToken(servletRequest, servletResponse)) {
                        return true;
                    } else {
                        handler401(servletResponse, AUTH_ANONYMOUS.getCode(), "token已过期,请重新登录");
                        return false;
                    }
                }
                handler401(servletResponse, AUTH_ANONYMOUS.getCode(), msg);
                return false;
            }
        }
    }

    /**
     * 此处为AccessToken刷新，进行判断RefreshToken是否过期，未过期就返回新的AccessToken且继续正常访问
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     */
    private boolean refreshToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取header，tokenStr
        String oldToken = request.getHeader("Authorization");
        String userNum = JWTUtil.getUserNum(oldToken);
        String key = CommonConstant.JWT_TOKEN + userNum;
        String keyU = CommonConstant.USER_SIMPLE_INFO +userNum;
        //获取redis tokenStr和缓存中的用户信息
        String redisToken = (String) redisTemplate.opsForValue().get(key);
        if (redisToken != null) {
            //如果token存在且token等于当前redis中的token，则刷新token时间
            if (oldToken.equals(redisToken)) {
                UserInfoVo vo = this.userMapper.selectByUserNum(userNum);
                //重写生成token(刷新)
                String newTokenStr = JWTUtil.sign(vo);
                JWTToken jwtToken = new JWTToken(newTokenStr);
                userService.addTokenToRedis(userNum, newTokenStr);
                userService.addUserInfoToRedis(userNum,vo);
                //放进threadLocal
                SessionLocal.setUserInfo(vo);
                SecurityUtils.getSubject().login(jwtToken);
                response.setHeader("Authorization", newTokenStr);
                return true;
            }
        }
        return false;
    }

    /**
     * token有问题
     *
     * @param response
     * @param code
     * @param msg
     */
    private void handler401(ServletResponse response, String code, String msg) {
        try {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpStatus.OK.value());
            httpResponse.setContentType("application/json;charset=utf-8");
            httpResponse.getWriter().write("{\"code\":" + code + ", \"msg\":\"" + msg + "\"}");
        } catch (IOException e) {
            log.error("token异常：{}",e);
        }
    }

    /**
     * 支持跨域
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Authorization,Origin,X-Requested-With,Content-Type,Accept");
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(httpServletRequest, httpServletResponse);
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("Authorization");
        return new JWTToken(token);
    }
}
