package com.hekiraku.gemini;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigInteger;
import java.util.List;

@SpringBootApplication
@MapperScan("com.hekiraku.gemini.mapper")
@Configuration
@EnableSwagger2
@Slf4j
public class GeminiApplication implements WebMvcConfigurer {
    @Autowired
    private HttpMessageConverters httpMessageConverters;

    public static void main(String[] args) {
        SpringApplication.run(GeminiApplication.class, args);
    }
    /*    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("进入方法addInterceptors");
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
        log.info("离开方法addInterceptors");
    }*/
}