package com.hekiraku.gemini.manager;

import com.hekiraku.gemini.domain.entity.ActiveLogEntity;
import com.hekiraku.gemini.domain.vo.ActiveLogVo;

import java.util.List;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/4/3
 * 功能说明：
 */
public interface ActiveLogManager {
    List<ActiveLogVo> selectAllByActiveName(String activeName);
    int create(ActiveLogEntity activeLogEntity);
}
