package com.hekiraku.gemini.controller.buss;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.entity.dto.TextRecordDto;
import com.hekiraku.gemini.entity.vo.TextRecordVo;
import com.hekiraku.gemini.service.TextRecordService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.hekiraku.gemini.common.enums.BussResultEnums.B_TEXT_SELECT;
import static com.hekiraku.gemini.common.enums.BussResultEnums.B_TEXT_UPDATE;

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
    @JsonView({TextRecordDto.WriteView.class})
    @ApiResponses({
            @ApiResponse(code = 10001,message = "更新/添加日记错误",response = ApiResult.class)
    })
    public ApiResult<TextRecordVo> writeRecord(@RequestBody TextRecordDto textRecordDto){
        //获取参数
        try{
            return textRecordService.writeRecord(textRecordDto);
        }catch (Exception e){
            log.error("更新日记传参为:{},异常信息:{}",textRecordDto,e);
            return ApiResult.buildFail(B_TEXT_UPDATE.getCode(),B_TEXT_UPDATE.getDesc());
        }

    }
    @ApiOperation(value = "查看日记", notes = "别看写了一个实体接收参数，但是参数只要传createDay，soulChar和userNum即可")
    @GetMapping("/read")
    @JsonView({TextRecordDto.ReadView.class})
    @ApiResponses({
            @ApiResponse(code = 10002,message = "读取日记错误",response = ApiResult.class)
    })
    public ApiResult readRecord(@RequestBody @JsonView(TextRecordDto.ReadView.class) TextRecordDto textRecordDto){
        //获取参数
        try{
            return textRecordService.readRecord(textRecordDto);
        }catch (Exception e){
            log.error("获取日记信息传参:{},异常信息：{}",textRecordDto,e);
            return ApiResult.buildFail(B_TEXT_SELECT.getCode(),B_TEXT_SELECT.getDesc());
        }

    }
}
