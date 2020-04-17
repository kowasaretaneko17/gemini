package com.hekiraku.gemini.service.impl;

import com.hekiraku.gemini.aop.threadLocal.SessionLocal;
import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.domain.vo.SoulCharVo;
import com.hekiraku.gemini.domain.vo.UserInfoVo;
import com.hekiraku.gemini.manager.TextRecordManager;
import com.hekiraku.gemini.service.DataRecordService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 根据日期/性格等获取相应的数据
 * 主要是个人页面上的日期记录获取
 */
@Service
public class DataRecordServiceImpl implements DataRecordService {
    @Autowired
    private TextRecordManager textRecordManager;
    /**
     * 获取某年全年的日记记录索引
     */
    @Override
    public ApiResult getYearsDiary(String years){
        String year = StringUtils.isEmpty(years)?new DateTime().year().toString():years;
        UserInfoVo userInfoVo = SessionLocal.getUserInfo();
        String userNum = userInfoVo.getUserNum();
        List<List<SoulCharVo>> result = textRecordManager.selectSoulDiaryByUserAndYear(year,userNum);
        return ApiResult.buildSuccessNormal("获取全年记录成功",result);
    }
}
