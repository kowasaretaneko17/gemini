package com.hekiraku.gemini.service.impl;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.common.constants.CommonConstant;
import com.hekiraku.gemini.entity.UserEntity;
import com.hekiraku.gemini.entity.vo.UserInfoVo;
import com.hekiraku.gemini.manager.UserManager;
import com.hekiraku.gemini.mapper.UserMapper;
import com.hekiraku.gemini.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.mgt.SimpleSession;
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
    @Autowired
    private UserMapper userMapper;

    @Override
    public ApiResult selectUserById(String id) {
        List<UserEntity> userEntities = userManager.selectById("1");
        return ApiResult.buildSuccessNormal("查询成功", userEntities);
    }

    @Override
    public ApiResult selectAllByUserName(String username) {
        List<UserInfoVo> userInfoVos = userManager.selectAllByUserName(username);
        return ApiResult.buildSuccessNormal("查询成功",userInfoVos);
    }

    @Override
    public UserInfoVo selectUserByToken(String token) {
        SimpleSession session = (SimpleSession) redisTemplate.opsForValue().get("shiro_redis_session"+token);
        UserInfoVo userInfoVo = (UserInfoVo) session.getAttribute("currentUser");
        return userInfoVo;
    }
    @Override
    public void addTokenToRedis(String userName, String jwtTokenStr) {
        String key = CommonConstant.JWT_TOKEN + userName;
        redisTemplate.opsForValue().set(key, jwtTokenStr, refreshJwtTokenExpireTime, TimeUnit.MINUTES);
    }
    @Override
    public Map<String, Object> createRandomToken(String textStr) {
        //生成一个token
        String sToken = UUID.randomUUID().toString();
        //生成验证码对应的token  以token为key  验证码为value存在redis中
        redisTemplate.opsForValue().set(CommonConstant.NO_REPEAT_PRE + sToken, textStr, identifyingTokenExpireTime, TimeUnit.MINUTES);
        Map<String, Object> map = new HashMap<>();
        map.put("cToken", sToken);
        return map;
    }
    @Override
    public boolean removeJWTToken(String userName) {
        String key = CommonConstant.JWT_TOKEN + userName;
        return redisTemplate.delete(key);
    }
}
