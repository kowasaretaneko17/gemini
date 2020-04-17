package com.hekiraku.gemini.manager.impl;

import com.auth0.jwt.algorithms.Algorithm;
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
    public UserInfoVo selectById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public UserInfoVo selectByUserName(String username) {
        return userMapper.selectByUserName(username);
    }

    @Override
    public UserInfoVo selectByUserNum(String usernum) {
        return userMapper.selectByUserNum(usernum);
    }

    @Override
    public UserInfoVo selectByNickName(String nickName) {
        return userMapper.selectByNickName(nickName);
    }

    @Override
    public UserInfoVo selectByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }

    @Override
    public UserInfoVo selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public int createUser(UserEntity userEntity) {
        return userMapper.createUser(userEntity);
    }

    @Override
    public int addRoleForUser(Long userId, String roleName) {
        Long roleId = roleMapper.selectIdByRoleName(roleName);
        return userMapper.addRoleForUser(roleId,userId);
    }
}
