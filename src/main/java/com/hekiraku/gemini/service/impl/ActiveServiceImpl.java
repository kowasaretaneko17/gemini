package com.hekiraku.gemini.service.impl;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.domain.vo.ActiveLogVo;
import com.hekiraku.gemini.manager.ActiveLogManager;
import com.hekiraku.gemini.service.ActiveLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/4/3
 * 功能说明：
 */
@Service
public class ActiveServiceImpl implements ActiveLogService {
    private ActiveLogManager activeLogManager;
    @Autowired
    private void setActiveLogManager(ActiveLogManager activeLogManager){
        this.activeLogManager = activeLogManager;
    }

    @Override
    public ApiResult<List<ActiveLogVo>> selectAllByActiveName(String activeName) {
            List<ActiveLogVo> activeLogVos = activeLogManager.selectAllByActiveName(activeName);
            return ApiResult.buildSuccessNormal("查询成功",activeLogVos);
    }
}
