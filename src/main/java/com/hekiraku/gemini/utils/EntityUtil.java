package com.hekiraku.gemini.utils;

import com.hekiraku.gemini.entity.vo.UserInfoVo;
import com.hekiraku.gemini.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param currentUserId 当前用户编号
     * @return 是否新增
     */
    public static boolean setCommonField(Object entity, String currentUserId) {
        try {
            Date updateTime = new Date();
            BeanUtils.setProperty(entity, "updateTime", updateTime);
            BeanUtils.setProperty(entity, "updateUserId", currentUserId);
            boolean isInsert = StringUtils.isEmpty(BeanUtils.getProperty(entity, "id"));
            if (isInsert) {
                BeanUtils.setProperty(entity, "id", Long.toString(System.currentTimeMillis()));
                BeanUtils.setProperty(entity, "createTime", updateTime);
                BeanUtils.setProperty(entity, "createUserId", currentUserId);
                BeanUtils.setProperty(entity, "rev", 0);
                BeanUtils.setProperty(entity, "deleteFlag", "0");
            }
            return isInsert;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("设置公共字段失败", e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
