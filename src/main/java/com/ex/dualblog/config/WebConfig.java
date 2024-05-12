package com.ex.dualblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ex.dualblog.utils.JwtInterceptor;

import jakarta.annotation.Resource;
import java.util.Arrays;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private JwtInterceptor jwtInterceptor;

    // 加自定义拦截器JwtInterceptor，设置拦截规则
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns(Arrays.asList("/user/**"))
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/create")
                .excludePathPatterns("/user/logout");

    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000") // 允许的来源域名
            .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的 HTTP 方法
            .allowCredentials(true); // 允许携带凭证（例如 Cookie）
    }
}
