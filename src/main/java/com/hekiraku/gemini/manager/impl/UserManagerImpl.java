package com.hekiraku.gemini.manager.impl;

import com.hekiraku.gemini.domain.entity.UserEntity;
import com.hekiraku.gemini.domain.vo.UserInfoVo;
import com.hekiraku.gemini.manager.UserManager;
import com.hekiraku.gemini.mapper.RoleMapper;
import com.hekiraku.gemini.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        Long roleId = roleMapper.selectIdByRoleName(roleName);
        return userMapper.addRoleForUser(roleId,userId);
    }
}
