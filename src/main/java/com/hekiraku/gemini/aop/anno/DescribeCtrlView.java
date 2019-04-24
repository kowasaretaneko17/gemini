package com.hekiraku.gemini.aop.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/24
 * 功能说明：使用JsonView的时候，更加明确的解释是哪个接口需要它
 */
@Target({ElementType.METHOD, ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DescribeCtrlView {
    String[] requestMappingPath() default "";
}
