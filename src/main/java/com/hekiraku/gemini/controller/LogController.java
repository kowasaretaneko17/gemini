package com.hekiraku.gemini.controller;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.domain.vo.ActiveLogVo;
import com.hekiraku.gemini.service.ActiveLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
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
    public ApiResult<List<ActiveLogVo>> activityLog(String activeName) {
        return activeLogService.selectAllByActiveName(activeName);
    }

}
