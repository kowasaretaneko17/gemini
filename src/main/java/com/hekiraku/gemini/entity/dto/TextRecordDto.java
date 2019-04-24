package com.hekiraku.gemini.entity.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.hekiraku.gemini.aop.anno.DescribeCtrlView;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/4
 * 功能说明：
 */
@Data
@ApiModel(value = "TextRecordDto",description = "日记传参对象")
public class TextRecordDto {

    public interface BaseView{};

    @DescribeCtrlView(requestMappingPath = "/record/write")
    @ApiModel(value = "WriteView",description = "写日记传参对象")
    public interface WriteView extends BaseView{};

    @ApiModel(value = "ReadView",description = "读取日记传参对象")
    @DescribeCtrlView(requestMappingPath = "/record/read")
    public interface ReadView extends BaseView{};

    @JsonView(ReadView.class)
    private String createDay;
    @JsonView(BaseView.class)
    private String soulChar;
    @JsonView(ReadView.class)
    private String userNum;
    @JsonView(WriteView.class)
    private String text;
}
