package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.entity.UserEntity;
import com.hekiraku.gemini.entity.vo.ResourceVo;
import com.hekiraku.gemini.entity.vo.RoleVo;
import com.hekiraku.gemini.entity.vo.UserInfoVo;
import com.hekiraku.gemini.provider.UserDynaSqlProvider;
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
    @SelectProvider(type=UserDynaSqlProvider.class,method = "selectByUserName")
    @ResultMap("userMap")
    UserInfoVo selectByUserName(String userName);

    @SelectProvider(type=UserDynaSqlProvider.class,method = "selectByUserNum")
    @ResultMap("userMap")
    UserInfoVo selectByUserNum(String userNum);
}
