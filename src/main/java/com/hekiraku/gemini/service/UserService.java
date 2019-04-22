package com.hekiraku.gemini.service;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.entity.vo.UserInfoVo;

import java.util.Map;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/22
 * 功能说明：
 */
public interface UserService {
    public ApiResult selectById(String id);
    public ApiResult selectByUserName(String username);
    public void addTokenToRedis(String userName, String jwtTokenStr);
    public Map<String, Object> createRandomToken(String textStr);
    public boolean removeJWTToken(String userName);
}
