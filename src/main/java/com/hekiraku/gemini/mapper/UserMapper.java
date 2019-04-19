package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.entity.UserEntity;
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
    @Select("select * from g_user where id = #{id}")
    UserEntity selectById(String id);

    @Select("select gu.*,gr.role_name from g_user gu,g_role gr,g_user_role gur where gu.id = #{id} and gur.user_id=gu.id and gur.role_id=gr.id")
    UserEntity selectAllById(String id);

    @SelectProvider(type=UserDynaSqlProvider.class,method = "selectAllByUserName")
    UserInfoVo selectAllByUserName(String userName);
}
