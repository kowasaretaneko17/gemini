package com.hekiraku.gemini.manager.impl;

import com.hekiraku.gemini.entity.UserEntity;
import com.hekiraku.gemini.entity.vo.UserInfoVo;
import com.hekiraku.gemini.manager.UserManager;
import com.hekiraku.gemini.mapper.RoleMapper;
import com.hekiraku.gemini.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/23
 * 功能说明：
 */
@Component
public class UserManagerImpl implements UserManager {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<UserEntity> selectById(String id) {
        List<UserEntity> userEntities = new ArrayList<>() ;
        UserEntity userEntity = userMapper.selectById(id);
        userEntities.add(userEntity);
        return userEntities;
    }

    @Override
    public List<UserInfoVo> selectAllByUserName(String username) {
        List<UserInfoVo> userInfoVos = new ArrayList<>() ;
        UserInfoVo userInfoVo = userMapper.selectAllByUserName(username);
        userInfoVos.add(userInfoVo);
        return userInfoVos;
    }
}
