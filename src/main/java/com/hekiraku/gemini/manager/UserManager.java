package com.hekiraku.gemini.manager;

import com.hekiraku.gemini.domain.entity.UserEntity;
import com.hekiraku.gemini.domain.vo.UserInfoVo;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/1/23
 * 功能说明：
 */
public interface UserManager {
    UserInfoVo selectByIdentityCode(String identityCode);
    UserInfoVo selectByUserId(Long userId);
    UserInfoVo selectByNickName(String nickName);
    UserInfoVo selectByPhone(String phone);
    UserInfoVo selectByEmail(String email);
    int createOrUpdateUser(UserEntity userEntity);
    int addRoleForUser(Long userId,String roleName);

}
