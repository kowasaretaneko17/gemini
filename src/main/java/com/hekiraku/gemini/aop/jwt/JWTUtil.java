package com.hekiraku.gemini.aop.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.*;
import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.domain.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.hekiraku.gemini.common.enums.AuthResultEnums.AUTH_TOKEN;

/**
 * Create by IntelliJ Idea 2018.1
 * Company: silita
 * Author: gemingyi
 * Date: 2018-08-09 15:19
 */
@Slf4j
@Component
public class JWTUtil {
    //token过期时间
    private static String tokenExpireTime;

    @Value("${jwt.tokenExpireTime}")
    public void setTokenExpireTime(String tokenExpireTime) {
        JWTUtil.tokenExpireTime = tokenExpireTime;
    }

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     * token中只存放userNum保障用户信息，userName其实虽然是账号唯一，但是其实账号也应该保护起来，不要暴露，还是唯一编码吧。
     */
    public static ApiResult verify(String token, String userNum, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("userNum", userNum).build();
            DecodedJWT jwt = verifier.verify(token);
            return ApiResult.successMsg("token检验正确");
        } catch (Exception e) {
            log.error("检验token异常:{}", e);
            return ApiResult.buildFailNormal(AUTH_TOKEN.getCode(), AUTH_TOKEN.getDesc(), e);
        }
    }

//    /**
//     * 获得token中的信息无需secret解密也能获得
//     *
//     * @return token中包含的用户名
//     */
//    public static String getUserName(String token) {
//        try {
//            DecodedJWT jwt = JWT.decode(token);
//            return jwt.getClaim("userName").asString();
//        } catch (JWTDecodeException e) {
//            log.error("获取token中用户名信息异常:{}", e);
//            return null;
//        }
//    }
    /**
     * 获取token中的usernum
     */
    public static String getUserNum(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userNum").asString();
        } catch (JWTDecodeException e) {
            log.error("获取token中用户编码信息异常:{}", e);
            return null;
        }
    }

    /**
     * 生成签名,60min后过期
     *
     * @return 加密的token
     */
    public static String sign(UserInfoVo userInfoVo) {
        try {
            //token过期时间
            Date date = new Date(System.currentTimeMillis() + (Long.parseLong(tokenExpireTime) * 60 * 1000));
            //密码MD5加密
            Algorithm algorithm = Algorithm.HMAC256(userInfoVo.getPassword());
            // usernum信息
            //删掉.withClaim("userName", userInfoVo.getUserName())，只在token中带上userNum就可以了
            return JWT.create()
                    .withClaim("userNum", userInfoVo.getUserNum()).withExpiresAt(date).sign(algorithm);
        } catch (Exception e) {
            log.error("生成签名异常:{}", e);
            return null;
        }
    }
    //这里是做token解析的。但是其实如果只用它做鉴权和验证，没有多大作用，因为我已经不再封装用户信息进去了，只为了刷新token，放一个用户编码。
    /**
     * 拷贝jwt包中的tokenutil方法
     */
    static String[] splitToken(String token) throws JWTDecodeException {
        String[] parts = token.split("\\.");
        if (parts.length == 2 && token.endsWith(".")) {
            parts = new String[]{parts[0], parts[1], ""};
        }

        if (parts.length != 3) {
            throw new JWTDecodeException(String.format("The token was expected to have 3 parts, but got %s.", parts.length));
        } else {
            return parts;
        }
    }
    /**
     * 拷贝JWTDecoder的方法
     */
    static public Map<String ,Object> jwtDecoder(String jwt){
        String[] parts = splitToken(jwt);
        JWTParser converter = new JWTParser();

        String headerJson;
        String payloadJson;
        try {
            headerJson = StringUtils.newStringUtf8(Base64.decodeBase64(parts[0]));
            payloadJson = StringUtils.newStringUtf8(Base64.decodeBase64(parts[1]));
        } catch (NullPointerException var6) {
            throw new JWTDecodeException("The UTF-8 Charset isn't initialized.", var6);
        }
        Map<String ,Object> map = new HashMap<>();
        Header header = converter.parseHeader(headerJson);
        Payload payload = converter.parsePayload(payloadJson);
        map.put("header",header);
        map.put("payload",payload);
        return map;
    }
}