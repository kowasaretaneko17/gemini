package com.hekiraku.gemini.entity.vo;

import com.hekiraku.gemini.entity.base.PaginationEntity;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/19
 * 功能说明：
 */
@Data
@Builder
public class ResourceVo extends PaginationEntity implements Serializable {
    private String resourceName;
}
