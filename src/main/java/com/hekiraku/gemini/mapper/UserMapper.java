package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.domain.entity.UserEntity;
import com.hekiraku.gemini.domain.vo.UserInfoVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/1/22
 * 功能说明：
 */
public interface UserMapper {

    UserInfoVo selectByUserId(Long userId);

    UserInfoVo selectByIdentityCode(String identityCode);

    UserInfoVo selectByNickName(String nickName);

    UserInfoVo selectByPhone(String phone);

    UserInfoVo selectByEmail(String email);

    int createOrUpdateUser(UserEntity userEntity);

    int addRoleForUser(Long roleId, Long userId);
}
