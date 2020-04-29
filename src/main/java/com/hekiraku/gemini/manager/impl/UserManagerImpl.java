package com.hekiraku.gemini.manager.impl;

import com.hekiraku.gemini.domain.entity.UserEntity;
import com.hekiraku.gemini.domain.vo.UserInfoVo;
import com.hekiraku.gemini.manager.UserManager;
import com.hekiraku.gemini.mapper.RoleMapper;
import com.hekiraku.gemini.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.Long.getLong;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
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
    public UserInfoVo selectByIdentityCode(String identityCode) {
        return userMapper.selectByIdentityCode(identityCode);
    }

    @Override
    public UserInfoVo selectByUserId(Long userId) {
        return userMapper.selectByUserId(userId);
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
    public int createOrUpdateUser(UserEntity userEntity) {
        return userMapper.createOrUpdateUser(userEntity);
    }

    @Override
    public int addRoleForUser(Long userId, String roleName) {
        Long roleId = roleMapper.selectByRoleCode(roleName).getRoleId();
        return userMapper.addRoleForUser(roleId,userId);
    }
}
