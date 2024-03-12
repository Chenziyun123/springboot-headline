package com.czy.config;

import com.czy.interceptor.LoginProtectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebMvcConfig
 * Package: com.czy.config
 * Description:
 *
 * @Author Ziyun Chen
 * @Create 2024/3/11 15:21
 * @Version 1.0  
 */
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginProtectInterceptor loginProtectInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(loginProtectInterceptor).addPathPatterns("/headline/**");
    }
}
