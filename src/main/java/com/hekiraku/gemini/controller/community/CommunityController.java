/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.controller.community;

import com.github.pagehelper.PageInfo;
import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.domain.dto.PageParamsDto;
import com.hekiraku.gemini.domain.dto.TextReadDto;
import com.hekiraku.gemini.domain.entity.TextSummaryEntity;
import com.hekiraku.gemini.service.TextRecordService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jcajce.provider.symmetric.TEA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.hekiraku.gemini.common.enums.CommunityResultEnums.C_TEXT_OPEN;

/**
 * @author bytedance<bytedance @ bytedance.com>
 * @task 社区
 * @date 04/22/2020 6:35 下午
 */
@RestController
@Api(value="社区模块", tags="社区模块")
@RequestMapping("/community")
@Slf4j
public class CommunityController {
    @Autowired
    TextRecordService textRecordService;
    @PostMapping("getOpenText")
    public ApiResult<PageInfo<TextSummaryEntity>> getOpenText(@RequestBody PageParamsDto pageParamsDto){
        try{
            return textRecordService.selectOpenTextByCreateDayAndSoulChar(new TextReadDto(),pageParamsDto);
        } catch (Exception e) {
            log.error("获取社区当天开放日及失败,异常为：{}",e);
            return ApiResult.buildFail(C_TEXT_OPEN.getCode(),C_TEXT_OPEN.getDesc());
        }
    }
}
