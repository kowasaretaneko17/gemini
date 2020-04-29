package com.hekiraku.gemini.domain.vo;

import com.hekiraku.gemini.domain.entity.ActiveLogEntity;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/4/3
 * 功能说明：
 */
@ToString(callSuper=true)
@EqualsAndHashCode
@ApiModel(value = "ActiveLogVo",description = "操作记录返回")
public class ActiveLogVo extends ActiveLogEntity {
}
