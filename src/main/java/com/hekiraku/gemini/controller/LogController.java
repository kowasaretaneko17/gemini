package com.hekiraku.gemini.controller;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.service.ActiveLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/4
 * 功能说明：
 */
@RestController
@Slf4j
@Api(value="日志模块", tags="日志模块")
public class LogController {
    @Autowired
    private ActiveLogService activeLogService;

    @ApiOperation(value = "根据用户动作获取用户操作日志", notes = "根据用户动作获取用户操作日志")
    @GetMapping("/activity")
    public ApiResult activityLog(String activeName) {
        return activeLogService.selectAllByActiveName(activeName);
    }

}
