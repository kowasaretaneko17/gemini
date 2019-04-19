package com.hekiraku.gemini.aop.shiro;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.hekiraku.gemini.aop.jwt.JWTToken;
import com.hekiraku.gemini.aop.jwt.JWTUtil;
import com.hekiraku.gemini.entity.vo.RoleVo;
import com.hekiraku.gemini.entity.vo.UserInfoVo;
import com.hekiraku.gemini.mapper.UserMapper;
import com.hekiraku.gemini.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/10/11.
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = JWTUtil.getUsername(principals.toString());
        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
        try {
            UserInfoVo vo = this.userMapper.selectAllByUserName(userName);
            auth.setRoles(vo.getSetRoles());
            auth.setStringPermissions((vo.getSetResources()));
        } catch (Exception e) {
            log.error("授权认证出现异常：{}",e);
        }
        return auth;
    }

    /**
     * 认证
     *
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得userName，用于和数据库进行对比
        String userName = JWTUtil.getUsername(token);
        UserInfoVo vo = this.userMapper.selectAllByUserName(userName);
        String redisUserInfo = (String) redisTemplate.opsForValue().get("token_jwt_" + userName);

        Map result = JWTUtil.verify(token, userName, vo.getPassword());
        Exception exception = (Exception) result.get("exception");

        if (vo == null) {
            throw new UnknownAccountException("该帐号不存在！");
        } else if (vo.getLock() == null || vo.getLock().equals(1)) {
            throw new UnknownAccountException("该帐号已被锁定！");
        } else if (exception != null && exception instanceof SignatureVerificationException) {
            throw new AuthenticationException("Token错误(Token incorrect.)！");
        } else if (exception != null && exception instanceof TokenExpiredException) {
            throw new AuthenticationException("Token已过期(Token expired.)！");
            //被T
        } else if(StringUtils.isEmpty(redisUserInfo)){
            throw new AuthenticationException("Token已失效(Token invalid.)！");
        }else {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(token, token, vo.getUserName());
            return authcInfo;
        }
    }
}
