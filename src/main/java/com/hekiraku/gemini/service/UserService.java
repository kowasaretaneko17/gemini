package com.hekiraku.gemini.service;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.entity.UserEntity;
import com.hekiraku.gemini.entity.dto.UserInfoDto;
import com.hekiraku.gemini.entity.vo.KaptchaVo;
import com.hekiraku.gemini.entity.vo.UserInfoVo;
import io.swagger.annotations.Api;

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
    public ApiResult<UserInfoVo> selectByUserName(String username);
    public ApiResult<UserInfoVo> selectByUserNum(String usernum) ;
    public void addTokenToRedis(String userNum, String jwtTokenStr);
    public void addUserInfoToRedis(String userNum, UserInfoVo userInfoVo);
    public void addCheckCode(String mail,String checkCode);
    public boolean signCheckCode(String mail,String checkCode);
    public KaptchaVo createRandomToken(String textStr);
    public boolean removeJWTToken(String userName);
    UserInfoVo selectByNickName(String nickName);
    UserInfoVo selectByPhone(String phone);
    UserInfoVo selectByEmail(String email);
    public int createUser(UserEntity userEntity);
}
