package com.hekiraku.gemini.aop.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author bytedance<bytedance @ bytedance.com>
 * @task
 * @date 05/12/2020 4:28 下午
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(JWT.class)
@Slf4j
public class JWTFilterTest {
    @Test
    public void dosome(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODkyNTI4NTEsInVzZXJJZCI6NDQ4MTcwNTA5MTYzNTYwOTYwfQ.tprDwBnIPNfQVMAx1_L1qmqgtYaZyabCdUYc8vb5haw";
        DecodedJWT decodedJWT = JWT.decode(token);
        log.info(decodedJWT.getClaim("userId").asLong().toString());
    }

}
