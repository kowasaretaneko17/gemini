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

//    @SelectProvider(type=UserDynaSqlProvider.class,method = "selectAllByUserName")
//    UserInfoVo selectAllByUserName(String userName);

    @SelectProvider(type=UserDynaSqlProvider.class,method = "selectById")
    @Results(id = "userMap",value = {
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.hekiraku.gemini.mapper.RoleMapper.selectById"))
    })
    UserInfoVo selectById(String id);
    @SelectProvider(type=UserDynaSqlProvider.class,method = "selectByIdentityCode")
    @ResultMap("userMap")
    UserInfoVo selectByIdentityCode(String identityCode);

    @SelectProvider(type=UserDynaSqlProvider.class,method = "selectByUserId")
    @ResultMap("userMap")
    UserInfoVo selectByUserId(Long userId);

    @SelectProvider(type=UserDynaSqlProvider.class,method = "selectByNickName")
    @ResultMap("userMap")
    UserInfoVo selectByNickName(String nickName);

    @SelectProvider(type=UserDynaSqlProvider.class,method = "selectByPhone")
    @ResultMap("userMap")
    UserInfoVo selectByPhone(String phone);

    @SelectProvider(type=UserDynaSqlProvider.class,method = "selectByEmail")
    @ResultMap("userMap")
    UserInfoVo selectByEmail(String email);

    @InsertProvider(type=UserDynaSqlProvider.class,method = "createOrUpdateUser")
    int createOrUpdateUser(UserEntity userEntity);

    @InsertProvider(type=UserDynaSqlProvider.class,method = "addRoleForUser")
    int addRoleForUser(Long roleId,Long userId);
}
