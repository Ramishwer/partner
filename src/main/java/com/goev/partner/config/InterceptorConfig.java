package com.goev.partner.config;


import com.goev.partner.config.interceptor.ApplicationSourceInterceptor;
import com.goev.partner.config.interceptor.AuthenticationInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@AllArgsConstructor
@EnableWebMvc
public class InterceptorConfig implements WebMvcConfigurer {

    private final AuthenticationInterceptor authenticationInterceptor;
    private final ApplicationSourceInterceptor applicationSourceInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(applicationSourceInterceptor);
        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/**").
                excludePathPatterns(
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-ui/**",
                        "/api/v1/status",
                        "/api/v1/partner-management/sessions/otp",
                        "/api/v1/partner-management/sessions",
                        "/api/v1/partner-management/sessions/**/token",
                        "/api/v1/partner-management/app-properties"

                );
    }
}