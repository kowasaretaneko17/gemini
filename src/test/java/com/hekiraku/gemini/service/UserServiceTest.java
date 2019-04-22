package com.hekiraku.gemini.service;

import com.hekiraku.gemini.common.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Test
    public void select(){
        ApiResult userEntities = userService.selectById("1");
        log.info("{}",userEntities);
    }
}