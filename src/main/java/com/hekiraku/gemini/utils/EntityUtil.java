package com.hekiraku.gemini.utils;

import com.hekiraku.gemini.aop.threadLocal.SessionLocal;
import com.hekiraku.gemini.domain.base.BaseEntity;
import com.hekiraku.gemini.domain.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * 实体常用工具方法
 *
 * @author wudidi
 */
@Slf4j
public class EntityUtil {

    private EntityUtil() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * 设置公共字段
     *
     * @param entity        实体
     * @return 是否新增
     */
    public static boolean setCommonField(Object entity) {
        UserInfoVo userInfoVo = SessionLocal.getUserInfo();
        try {
            BeanUtils.setProperty(entity, "updateUserId", userInfoVo.getUserId());
            BeanUtils.setProperty(entity, "createUserId", userInfoVo.getUserId());
            return true;
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("设置公共字段失败", e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
