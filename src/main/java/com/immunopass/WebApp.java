package com.immunopass;

import com.immunopass.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@EnableWebMvc
@Configuration
public class WebApp implements WebMvcConfigurer {

    // TODO add otp end points
    public static List<String> AUTH_EXCLUDE_PATTERNS = new ArrayList<>();

    public AuthInterceptor getAuthInterceptor() {
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthInterceptor()).
                addPathPatterns("/**").
                excludePathPatterns(AUTH_EXCLUDE_PATTERNS);
    }
}

