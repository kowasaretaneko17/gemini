package com.hekiraku.gemini.manager;

import com.hekiraku.gemini.entity.ActiveLogEntity;
import com.hekiraku.gemini.entity.vo.ActiveLogVo;

import java.util.List;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/3
 * 功能说明：
 */
public interface ActiveLogManager {
    List<ActiveLogVo> selectAllByActiveName(String activeName);
    int create(ActiveLogEntity activeLogEntity);
}
