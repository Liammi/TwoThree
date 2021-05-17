package com.example.video.config;

import com.example.video.interceptor.AdminInterceptor;
import com.example.video.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/jump")
                .excludePathPatterns("/")
                .excludePathPatterns("/register/**")
                .excludePathPatterns("/admin/**");
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/jump").excludePathPatterns("/admin/login").excludePathPatterns("/admin/register/**");

    }

}
