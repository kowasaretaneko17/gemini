package com.hekiraku.gemini.service.impl;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.common.constants.CommonConstant;
import com.hekiraku.gemini.entity.UserEntity;
import com.hekiraku.gemini.entity.vo.KaptchaVo;
import com.hekiraku.gemini.entity.vo.UserInfoVo;
import com.hekiraku.gemini.manager.UserManager;
import com.hekiraku.gemini.mapper.UserMapper;
import com.hekiraku.gemini.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/22
 * 功能说明：
 */
@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {
    @Value("${redis.identifyingTokenExpireTime}")
    private long identifyingTokenExpireTime;
    //redis中jwtToken过期时间
    @Value("${redis.refreshJwtTokenExpireTime}")
    private long refreshJwtTokenExpireTime;

    @Autowired
    private UserManager userManager;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public ApiResult selectById(String id) {
        UserInfoVo userInfoVo = userManager.selectById(id);
        return ApiResult.buildSuccessNormal("查询成功",userInfoVo);
    }

    @Override
    public ApiResult<UserInfoVo> selectByUserName(String username) {
        UserInfoVo userInfoVo = userManager.selectByUserName(username);
        return ApiResult.buildSuccessNormal("查询成功",userInfoVo);
    }

    @Override
    public void addTokenToRedis(String userName, String jwtTokenStr) {
        //userName是唯一的。
        String key = CommonConstant.JWT_TOKEN + userName ;
        //集合类型不存在设置每一个key的过期时间，所以其实最后还是只能用string类型。
//        redisTemplate.opsForHash().put("token",key,jwtTokenStr);
        redisTemplate.opsForValue().set(key, jwtTokenStr, refreshJwtTokenExpireTime, TimeUnit.MINUTES);
    }
    @Override
    public KaptchaVo createRandomToken(String textStr) {
        //生成一个token
        String sToken = UUID.randomUUID().toString();
        //生成验证码对应的token  以token为key  验证码为value存在redis中
        redisTemplate.opsForValue().set(CommonConstant.NO_REPEAT_PRE + sToken, textStr, identifyingTokenExpireTime, TimeUnit.MINUTES);
        KaptchaVo kaptcha =new KaptchaVo();
        kaptcha.setCToken(sToken);
        return kaptcha;
    }
    @Override
    public boolean removeJWTToken(String userName) {
        String key = CommonConstant.JWT_TOKEN + userName;
        return redisTemplate.delete(key);
    }
}
