package com.hekiraku.gemini.service;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.domain.entity.UserEntity;
import com.hekiraku.gemini.domain.vo.KaptchaVo;
import com.hekiraku.gemini.domain.vo.UserInfoVo;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/1/22
 * 功能说明：
 */
public interface UserService {
    public ApiResult<UserInfoVo> selectByIdentityCode(String identityCode);
    public ApiResult<UserInfoVo> selectByUserId(Long userId) ;
    public void addTokenToRedis(String userNum, String jwtTokenStr);
    public void addUserInfoToRedis(String userNum, String userInfoVo);
    public void addCheckCode(String mail,String checkCode);
    public boolean signCheckCode(String mail,String checkCode);
    public KaptchaVo createRandomToken(String textStr);
    public boolean removeJWTToken(String userName);
    UserInfoVo selectByNickName(String nickName);
    UserInfoVo selectByPhone(String phone);
    UserInfoVo selectByEmail(String email);
    public int createOrUpdateUser(UserEntity userEntity);
}
