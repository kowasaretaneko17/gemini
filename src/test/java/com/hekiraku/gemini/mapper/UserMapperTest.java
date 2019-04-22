package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.entity.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserMapperTest {
    @Autowired(required=true)
    UserMapper userMapper;

    @Test
    public void select(){
        UserInfoVo userEntity = userMapper.selectById("1");
        log.info("{}",userEntity);
    }
    @Test
    public void selectAll(){
        UserInfoVo userEntity = userMapper.selectByUserName("zhangsan");
        log.info("{}",userEntity);
    }

}