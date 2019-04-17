package com.hekiraku.gemini.controller.buss;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.entity.dto.TextRecordDto;
import com.hekiraku.gemini.service.TextRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/4
 * 功能说明：
 */
@RestController
@Slf4j
@Api(value = "日记模块", tags = "日记模块")
@RequestMapping("/record")
public class TextRecordController {
    private TextRecordService textRecordService;
    @Autowired
    private void setTextRecordService(TextRecordService textRecordService){
        this.textRecordService = textRecordService;
    }

    @ApiOperation(value = "写日记", notes = "别看写了一个实体接收参数，但是参数只要传soulChar和text即可")
    @PostMapping("/write")
    public ApiResult writeRecord(@RequestBody TextRecordDto textRecordDto,@RequestHeader("Authorization") String token){
        //获取参数
        try{
            return textRecordService.writeRecord(textRecordDto,token);
        }catch (Exception e){
            log.error("{}",e);
            return ApiResult.buildFail("100","更新日记异常");
        }

    }
    @ApiOperation(value = "查看日记", notes = "别看写了一个实体接收参数，但是参数只要传createDay，soulChar和userNum即可")
    @GetMapping("/read")
    public ApiResult readRecord(@RequestBody TextRecordDto textRecordDto,@RequestHeader("Authorization") String token){
        //获取参数
        try{
            return textRecordService.readRecord(textRecordDto,token);
        }catch (Exception e){
            log.error("{}",e);
            return ApiResult.buildFail("100","查看日记异常");
        }

    }
}
