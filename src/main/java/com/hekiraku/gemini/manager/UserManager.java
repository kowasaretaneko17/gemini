package com.hekiraku.gemini.manager;

import com.hekiraku.gemini.entity.UserEntity;
import com.hekiraku.gemini.entity.vo.UserInfoVo;
import com.hekiraku.gemini.provider.UserDynaSqlProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/23
 * 功能说明：
 */
public interface UserManager {
    UserInfoVo selectById(String id);
    UserInfoVo selectByUserName(String username);
    UserInfoVo selectByUserNum(String usernum);
    UserInfoVo selectByNickName(String nickName);
    UserInfoVo selectByPhone(String phone);
    UserInfoVo selectByEmail(String email);
    int createUser(UserEntity userEntity);
    int addRoleForUser(Long userId,String roleName);

}
