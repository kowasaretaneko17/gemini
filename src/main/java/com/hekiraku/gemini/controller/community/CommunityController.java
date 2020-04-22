/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.controller.community;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.domain.dto.TextReadDto;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("getOpenText")
    public ApiResult getOpenText(){
        return null;
    }
}
