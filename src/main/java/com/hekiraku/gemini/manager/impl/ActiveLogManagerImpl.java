package com.hekiraku.gemini.manager.impl;

import com.hekiraku.gemini.domain.entity.ActiveLogEntity;
import com.hekiraku.gemini.domain.vo.ActiveLogVo;
import com.hekiraku.gemini.manager.ActiveLogManager;
import com.hekiraku.gemini.mapper.ActiveLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
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
