package com.hekiraku.gemini.service.impl;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.entity.UserEntity;
import com.hekiraku.gemini.entity.vo.UserInfoVo;
import com.hekiraku.gemini.manager.UserManager;
import com.hekiraku.gemini.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.mgt.SimpleSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private UserManager userManager;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public ApiResult selectUserById(String id) {
        List<UserEntity> userEntities = userManager.selectById("1");
        return ApiResult.buildSuccessNormal("0000", "查询成功", userEntities);
    }

    @Override
    public ApiResult selectAllByUserName(String username) {
        List<UserInfoVo> userInfoVos = userManager.selectAllByUserName(username);
        return ApiResult.buildSuccessNormal("0000","查询成功",userInfoVos);
    }

    @Override
    public UserInfoVo selectUserByToken(String token) {
        SimpleSession session = (SimpleSession) redisTemplate.opsForValue().get("shiro_redis_session"+token);
        UserInfoVo userInfoVo = (UserInfoVo) session.getAttribute("currentUser");

//        UserInfoVo userInfoVo = UserInfoVo.builder().build();
//        userInfoVo.setNickName("aa");
//        redisTemplate.opsForValue().set("a", userInfoVo);
//        Object a = redisTemplate.opsForValue().get("a");
//        System.out.println(a);
//        //        UserInfoVo userInfoVo = (UserInfoVo) session.getAttributes().get("currentUser");
//        UserInfoVo u = (UserInfoVo) a;
//
//        Test test = new Test();
//        test.setName("a");
////        redisTemplate.opsForValue().set("a", test);
////        Test a = (Test) redisTemplate.opsForValue().get("a");
//        redisTemplate.boundValueOps("a").set(test);
//        Object a = redisTemplate.boundValueOps("a").get();
//        Test  aa = (Test) a;


        return userInfoVo;
    }
}
