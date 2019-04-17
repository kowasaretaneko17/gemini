package com.hekiraku.gemini.entity.vo;

import com.hekiraku.gemini.entity.TextRecordEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/4
 * 功能说明：
 */
@Data
public class TextRecordVo implements Serializable {
    private String text;
    private String soulChar;
    private String createDay;
}
