package com.hekiraku.gemini;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.hekiraku.gemini.mapper")
@Configuration
@EnableSwagger2
@Slf4j
public class GeminiApplication implements WebMvcConfigurer {

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