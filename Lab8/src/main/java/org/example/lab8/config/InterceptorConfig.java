package org.example.lab8.config;

import org.example.lab8.interceptor.AuthInterceptor;
import org.example.lab8.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;

    @Autowired
    LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/admin/**", "/account/change-password", "/account/edit-profile", "/order/**")
                .excludePathPatterns("/admin/home/index");

        // logInterceptor: apply to secured URIs (same list)
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/admin/**", "/account/change-password", "/account/edit-profile", "/order/**")
                .excludePathPatterns("/admin/home/index");
    }
}
