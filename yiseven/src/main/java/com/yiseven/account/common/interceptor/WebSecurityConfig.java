package com.yiseven.account.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hdeng
 */
@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    @Autowired
    UserValidInterceptor userValidInterceptor;
    @Autowired
    AddRecordValidInterceptor addRecordValidInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration1 = registry.addInterceptor(userValidInterceptor);
        registration1.addPathPatterns("/**");
        registration1.excludePathPatterns("/login", "/user/add")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");

        InterceptorRegistration registration2 = registry.addInterceptor(addRecordValidInterceptor);
        registration2.addPathPatterns("/record/add");
    }

}
