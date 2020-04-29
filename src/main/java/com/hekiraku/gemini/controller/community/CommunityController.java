/**
 * *****************************************************
 * Copyright (C) 2020 geminiif.com.cn. All Rights Reserved
 * This file is part of gemini center tech project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.controller.community;

import com.github.pagehelper.PageInfo;
import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.domain.dto.PageParamsDto;
import com.hekiraku.gemini.domain.dto.TextReadDto;
import com.hekiraku.gemini.domain.entity.TextDetailEntity;
import com.hekiraku.gemini.domain.entity.TextSummaryEntity;
import com.hekiraku.gemini.service.TextRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jcajce.provider.symmetric.TEA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.hekiraku.gemini.common.enums.CommunityResultEnums.C_TEXT_OPEN;
import static com.hekiraku.gemini.common.enums.CommunityResultEnums.C_TEXT_OPEN_DETAIL;

/**
 * @author hekiraku<hekiraku@foxmail.com>
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
    @ApiOperation(value = "获取社区当天开放日记", notes = "分页，参数是页码和每页展示数量")
    public ApiResult<PageInfo<TextSummaryEntity>> getOpenText(@RequestBody PageParamsDto pageParamsDto){
        try{
            return textRecordService.selectOpenTextByCreateDayAndSoulChar(new TextReadDto(),pageParamsDto);
        } catch (Exception e) {
            log.error("获取社区当天开放日记失败,异常为：{}",e);
            return ApiResult.buildFail(C_TEXT_OPEN.getCode(),C_TEXT_OPEN.getDesc());
        }
    }
    @GetMapping("getTextDetail")
    @ApiOperation(value = "获取日记详情", notes = "传参是textId，可以通过日记列表获取，form-data格式")
    public ApiResult<TextDetailEntity> getTextDetail(Long textId){
        try{
            return textRecordService.selectTextDetailByTextId(textId);
        }catch (Exception e){
            log.error("获取日记详情失败,异常为：{}",e);
            return ApiResult.buildFail(C_TEXT_OPEN_DETAIL.getCode(),C_TEXT_OPEN_DETAIL.getDesc());
        }
    }
}
