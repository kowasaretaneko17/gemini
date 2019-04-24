package com.hekiraku.gemini.controller.buss;

import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.entity.dto.TextRecordDto;
import com.hekiraku.gemini.entity.vo.TextRecordVo;
import com.hekiraku.gemini.service.TextRecordService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.hekiraku.gemini.common.enums.ExceptionResultEnums.E_QUERY_NOTE;
import static com.hekiraku.gemini.common.enums.ExceptionResultEnums.E_UPDATE_NOTE;

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
    @ApiResponses(value={@ApiResponse(code=200, message="OK")})
    public ApiResult<TextRecordVo> writeRecord(@RequestBody TextRecordDto textRecordDto){
        //获取参数
        try{
            return textRecordService.writeRecord(textRecordDto);
        }catch (Exception e){
            log.error("更新日记传参为:{},异常信息:{}",textRecordDto,e);
            return ApiResult.buildFail(E_UPDATE_NOTE.getCode(),E_UPDATE_NOTE.getDesc());
        }

    }
    @ApiOperation(value = "查看日记", notes = "别看写了一个实体接收参数，但是参数只要传createDay，soulChar和userNum即可")
    @GetMapping("/read")
    public ApiResult readRecord(@RequestBody TextRecordDto textRecordDto){
        //获取参数
        try{
            return textRecordService.readRecord(textRecordDto);
        }catch (Exception e){
            log.error("获取日记信息传参:{},异常信息：{}",textRecordDto,e);
            return ApiResult.buildFail(E_QUERY_NOTE.getCode(),E_QUERY_NOTE.getDesc());
        }

    }
}
