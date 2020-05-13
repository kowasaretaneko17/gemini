/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.aop.converters;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigInteger;
import java.util.List;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
 * 日期:2019/4/30
 * 功能说明：全局类型转换器
 * 需要configuration的注解，把它当作一个配置类；
 * 需要继承 WebMvcConfigurationSupport ，把配置装配进去
 * 实现这个WebMvcConfigurer类貌似一直无效；
 */
@Configuration
public class MyConvertersConfiguration implements WebMvcConfigurer {
    //重写jackson的转换器方法，添加自定义的规则
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        //如果属性为空""或者null都不序列化，返回的json中没有这个字段，对移动端更省流量
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        SimpleModule simpleModule = new SimpleModule();
        //配置Long型转化为String的规则
        simpleModule.addSerializer(Long.class, new ToStringSerializer());
        simpleModule.addSerializer(Long.TYPE, new ToStringSerializer());
        //配置BigInteger型转化为String的规则
        simpleModule.addSerializer(BigInteger.class, new ToStringSerializer());
        objectMapper.registerModule(simpleModule);
        converter.setObjectMapper(objectMapper);
        //加在转换器最前面，不然不生效了。
        converters.add(0,converter);
    }

}
