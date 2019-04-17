package com.hekiraku.gemini.controller;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserControllerTest {
    @Autowired
    UserController userController;
    @Test
    public void select(){
        ApiResult<List<UserEntity>> listApiResult = userController.selectUserById();
        log.info("{},{},{},{}",listApiResult,listApiResult.getCode(),listApiResult.getMessage(),listApiResult.getData());
    }

}