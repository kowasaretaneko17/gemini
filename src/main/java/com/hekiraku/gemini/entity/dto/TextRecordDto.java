package com.hekiraku.gemini.entity.dto;

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
public class TextRecordDto {
    private String createDay;
    @NotNull
    private String soulChar;
    private String userNum;
    private String text;
}
