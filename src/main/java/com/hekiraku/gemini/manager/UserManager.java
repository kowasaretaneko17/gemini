package com.hekiraku.gemini.manager;

import com.hekiraku.gemini.entity.UserEntity;
import com.hekiraku.gemini.entity.vo.UserInfoVo;

import java.util.List;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/23
 * 功能说明：
 */
public interface UserManager {
    public UserInfoVo selectById(String id);
    public UserInfoVo selectByUserName(String username);
    public UserInfoVo selectByUserNum(String usernum);
}
