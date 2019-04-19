package com.hekiraku.gemini.entity;

import com.hekiraku.gemini.entity.base.BaseEntity;
import lombok.*;

import java.util.Date;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/4
 * 功能说明：
 */
@Data
@Builder
@ToString(callSuper = true)
public class TextRecordEntity extends BaseEntity {
    private String userNum;
    private String text;
    private String soulChar;
    private String createDay;
}
