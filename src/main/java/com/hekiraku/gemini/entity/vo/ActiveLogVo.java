package com.hekiraku.gemini.entity.vo;

import com.hekiraku.gemini.entity.ActiveLogEntity;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/3
 * 功能说明：
 */
@ToString(callSuper=true)
@EqualsAndHashCode
@ApiModel(value = "ActiveLogVo",description = "操作记录返回")
public class ActiveLogVo extends ActiveLogEntity {
}
