package com.hekiraku.gemini.manager.impl;

import com.hekiraku.gemini.entity.ActiveLogEntity;
import com.hekiraku.gemini.entity.vo.ActiveLogVo;
import com.hekiraku.gemini.manager.ActiveLogManager;
import com.hekiraku.gemini.mapper.ActiveLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/3
 * 功能说明：
 */
@Component("activeLogManager")
public class ActiveLogManagerImpl implements ActiveLogManager {
    @Autowired
    private ActiveLogMapper activeLogMapper;

    @Override
    public List<ActiveLogVo> selectAllByActiveName(String activeName) {
        List<ActiveLogVo> activeLogVos = activeLogMapper.selectAllByActiveName(activeName);
        return activeLogVos;
    }

    @Override
    public int create(ActiveLogEntity activeLogEntity) {
        return activeLogMapper.create(activeLogEntity);
    }

}
