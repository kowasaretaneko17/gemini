package com.hekiraku.gemini.aop.shiro;

import com.hekiraku.gemini.entity.vo.UserInfoVo;
import com.hekiraku.gemini.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/23
 * 功能说明：
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {
    private UserMapper userMapper;

    @Autowired
    private void setUserManager(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("————角色权限认证方法————");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        String role = userMapper.selectAllByUserName(username).getRoleName();
        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        set.add(role);
        //超级管理员权限
        if("admin".equals(role)){
            set.add("user");
        }
        //设置该用户拥有的角色
        info.setRoles(set);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        log.info("————身份认证方法————");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = (String) token.getPrincipal();
        // 从数据库获取对应用户名密码的用户
        UserInfoVo userInfoVo = userMapper.selectAllByUserName(token.getUsername());
        String password = userInfoVo.getPassword();
        log.info("username:{},password:{}",token.toString(),password);
        if ((null == password) ||(!password.equals(new String((char[]) token.getCredentials())))) {
            throw new AccountException("用户名或密码不正确");
        } else{
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
            SecurityUtils.getSubject().getSession().setAttribute("currentUser",userInfoVo);
            return authcInfo;
        }
    }
}
