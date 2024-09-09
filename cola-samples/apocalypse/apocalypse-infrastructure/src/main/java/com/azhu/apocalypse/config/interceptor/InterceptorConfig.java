package com.azhu.apocalypse.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



/**
 * @author azhu
 * @date 2021/11/3 2:07 下午
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");
    }
}
