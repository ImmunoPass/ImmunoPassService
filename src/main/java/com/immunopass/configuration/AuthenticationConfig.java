package com.immunopass.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.immunopass.interceptor.AuthInterceptor;


@Configuration
public class AuthenticationConfig implements WebMvcConfigurer {

    @Bean
    public AuthInterceptor getAuthInterceptor() {
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/v1/send_otp")
                .excludePathPatterns("/v1/verify_otp")
                .pathMatcher(new AntPathMatcher());
    }
}
