package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.domain.entity.UserEntity;
import com.hekiraku.gemini.domain.vo.UserInfoVo;
import com.hekiraku.gemini.mapper.provider.UserDynaSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/22
 * 功能说明：
 */
public interface UserMapper {

    @Results(id = "userMap",value = {
            @Result(property = "roles",column = "roleId",javaType = List.class,many = @Many(select = "com.hekiraku.gemini.mapper.RoleMapper.selectByRoleId"))
    })
    UserInfoVo selectByUserId(Long userId);

    @ResultMap("userMap")
    UserInfoVo selectByIdentityCode(String identityCode);

    @ResultMap("userMap")
    UserInfoVo selectByNickName(String nickName);

    @ResultMap("userMap")
    UserInfoVo selectByPhone(String phone);

    @ResultMap("userMap")
    UserInfoVo selectByEmail(String email);

    int createOrUpdateUser(UserEntity userEntity);

    int addRoleForUser(Long roleId,Long userId);
}
