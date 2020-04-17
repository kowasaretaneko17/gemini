package com.hekiraku.gemini.aop.threadLocal;

import com.hekiraku.gemini.domain.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/22
 * 功能说明：不想每次都传http参数，使用线程存登录信息
 */
@Slf4j
public class SessionLocal {
    private static ThreadLocal<UserInfoVo> local = new ThreadLocal<UserInfoVo>();

    /**
     * 设置用户信息
     *
     * @param userInfo
     */
    public static void setUserInfo( UserInfoVo userInfo )
    {
        local.set(userInfo) ;
        log.info("存入用户信息:{}",userInfo);
    }

    /**
     * 获取登录用户信息
     *
     * @return
     */
    public static UserInfoVo getUserInfo()
    {
        log.info("当前线程id：{}",Thread.currentThread().getName());
        return local.get();
    }
    /**
     * 删除掉储存的用户信息
     */
    public static void remove(){
        local.remove();
    }
}
