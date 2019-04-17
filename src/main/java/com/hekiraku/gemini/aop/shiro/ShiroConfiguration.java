package com.hekiraku.gemini.aop.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/23
 * 功能说明：
 * Shiro 配置
 * Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 * 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。
 **/
@Configuration
@Slf4j
public class ShiroConfiguration {

    private long sessionLive=30;
    private String sessionPrefix="shiro_redis_session";
    private long cacheLive=30;
    private String cachePrefix="shiro_redis_cache";

    /**
     * 自定义shiro cache管理
     *
     * @return
     */
    @Bean(name = "redisCacheManager")
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        //cache过期时间及前缀
        redisCacheManager.setCacheLive(cacheLive);
        redisCacheManager.setCacheKeyPrefix(cachePrefix);
        redisCacheManager.setRedisTemplate(redisTemplate);
        return redisCacheManager;
    }

    /**
     * 凭证匹配器（密码加密）
     *
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //加密算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //加密的次数
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * Session ID生成管理器
     *
     * @return
     */
    @Bean(name = "sessionIdGenerator")
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        JavaUuidSessionIdGenerator sessionIdGenerator = new JavaUuidSessionIdGenerator();
        return sessionIdGenerator;
    }

    /**
     * 自定义shiro session
     *
     * @return
     */
    @Bean(name = "redisSessionDAO")
    public RedisSessionDAO redisSessionDAO(JavaUuidSessionIdGenerator sessionIdGenerator, RedisTemplate redisTemplate) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setSessionIdGenerator(sessionIdGenerator);
        //session过期时间及前缀
        redisSessionDAO.setSessionLive(sessionLive);
        redisSessionDAO.setSessionKeyPrefix(sessionPrefix);
        redisSessionDAO.setRedisTemplate(redisTemplate);
        return redisSessionDAO;
    }

    /**
     * 自定义sessionManager
     *
     * @return
     */
    @Bean(name = "sessionManager")
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        MySessionManager mySessionManager = new MySessionManager();
        mySessionManager.setSessionDAO(redisSessionDAO);
        return mySessionManager;
    }

    @Bean(name = "customRealm")
    public CustomRealm myRealm() {
        CustomRealm myShiroRealm = new CustomRealm();
        //        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        myShiroRealm.setCachingEnabled(true);
        //        myShiroRealm.setCacheManager(redisCacheManager());
        return myShiroRealm;
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager(SessionManager sessionManager, RedisCacheManager redisCacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(redisCacheManager);
        return securityManager;
    }

//    @Bean(name = "kickoutSessionControlFilter")
//    public KickoutSessionControlFilter jwtFilter(SessionManager sessionManager, RedisTemplate redisTemplate) {
//        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
//        kickoutSessionControlFilter.setSessionManager(sessionManager);
//        kickoutSessionControlFilter.setRedisTemplate(redisTemplate);
//        kickoutSessionControlFilter.setKickoutPrefix(kickoutPrefix);
//        return kickoutSessionControlFilter;
//    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = new HashedMap(2);
        shiroFilterFactoryBean.setFilters(filters);
        //注意拦截链配置顺序 不能颠倒
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap();
        /**setLoginUrl如果不设置值，默认会自动寻找web工程根目录下的/login映射,或者/login.jsp。未登录页面*/
        shiroFilterFactoryBean.setLoginUrl("/notLogin");
        /**设置无权限时跳转的url*/
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
        /**登陆成功后跳转页面*/
        shiroFilterFactoryBean.setSuccessUrl("/index");
        /**以下是不会被拦截的连接*/
        /**druid+swagger平台*/
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/doc.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/swagger/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        /**游客，开发权限*/
        filterChainDefinitionMap.put("/guest/**", "anon");
        /**防止登录成功之后下载favicon.ico(不显示那个图标)*/
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        /**开放登录接口*/
        filterChainDefinitionMap.put("/login", "anon");
        /**用户，需要角色权限“user”*/
        filterChainDefinitionMap.put("/user/**", "roles[user]");
        /**管理员，需要角色权限“admin”*/
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
        /**其余接口一律拦截。要设置在所有权限设置的最后，不然会导致所有url都被拦截的*/
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 不加这个报错
     * @return
     */
    @Bean
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    /**
     * 加这个本类@Values取不到值...cao
     *
     * @return
     */
    //    @Bean(name = "lifecycleBeanPostProcessor")
    //    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    //        return new LifecycleBeanPostProcessor();
    //    }

    /**
     * 下面2个支持controller层注解实现权限控制
     *
     * @return
     */
    @Bean(name = "advisorAutoProxyCreator")
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager( securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * <p>
     * Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
//    @Bean(name = "shiroFilter")
//    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
//        log.info("进入shiroFilter");
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        /**必须设置 SecurityManager*/
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        /**setLoginUrl如果不设置值，默认会自动寻找web工程根目录下的/login映射,或者/login.jsp。未登录页面*/
//        shiroFilterFactoryBean.setLoginUrl("/notLogin");
//        /**设置无权限时跳转的url*/
//        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
//        /**登陆成功后跳转页面*/
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//
//        //拦截器.
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        /**以下是不会被拦截的连接*/
//        /**druid+swagger平台*/
//        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
//        filterChainDefinitionMap.put("/doc.html", "anon");
//        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
//        filterChainDefinitionMap.put("/swagger/**", "anon");
//        filterChainDefinitionMap.put("/v2/api-docs", "anon");
//        filterChainDefinitionMap.put("/webjars/**", "anon");
//        filterChainDefinitionMap.put("/druid/**", "anon");
//        /**游客，开发权限*/
//        filterChainDefinitionMap.put("/guest/**", "anon");
//        /**防止登录成功之后下载favicon.ico(不显示那个图标)*/
//        filterChainDefinitionMap.put("/favicon.ico", "anon");
//        filterChainDefinitionMap.put("/static/**", "anon");
//        /**开放登录接口*/
//        filterChainDefinitionMap.put("/login", "anon");
//        /**用户，需要角色权限“user”*/
//        filterChainDefinitionMap.put("/user/**", "roles[user]");
//        /**管理员，需要角色权限“admin”*/
//        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
//        /**其余接口一律拦截。要设置在所有权限设置的最后，不然会导致所有url都被拦截的*/
//        filterChainDefinitionMap.put("/**", "authc");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        log.info("退出shiroFilter");
//        return shiroFilterFactoryBean;
//    }
}
