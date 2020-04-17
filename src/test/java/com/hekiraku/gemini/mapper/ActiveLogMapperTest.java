package com.hekiraku.gemini.mapper;

import com.hekiraku.gemini.common.enums.LogActiveNameEnums;
import com.hekiraku.gemini.common.enums.LogActiveProjectEnums;
import com.hekiraku.gemini.common.enums.LogActiveTypeEnums;
import com.hekiraku.gemini.domain.entity.ActiveLogEntity;
import com.hekiraku.gemini.domain.vo.ActiveLogVo;
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
public class ActiveLogMapperTest {
    @Autowired
    private ActiveLogMapper activeLogMapper;
    @Test
    public void create() {
        ActiveLogEntity activeLogEntity = new ActiveLogEntity.Builder(LogActiveProjectEnums.GEMINI,LogActiveTypeEnums.SYSTEM,"123",LogActiveNameEnums.LOG_LOGIN,"123").build();
        Integer integer = activeLogMapper.create(activeLogEntity);
        log.info("{}",integer);
    }
    @Test
    public void select() {
//        ActiveLogEntity activeLogEntity = new ActiveLogEntity.Builder(LogActiveProjectEnums.GEMINI,LogActiveTypeEnums.SYSTEM,"123",LogActiveNameEnums.LOG_LOGIN,"123").build();
        List<ActiveLogVo> activeLogEntity = activeLogMapper.selectAllByActiveName("001");
        log.info("{}",activeLogEntity);

    }
}