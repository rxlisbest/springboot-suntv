package net.ruixinglong.suntv.config;

import net.ruixinglong.suntv.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class AuthorizationConfig implements WebMvcConfigurer {

    @Bean
    AuthorizationInterceptor authorizationInterceptor() {
        return new AuthorizationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(authorizationInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/sms/create")
                .excludePathPatterns("/users/login")
                .excludePathPatterns("/users/register")
                .excludePathPatterns("/users/password-login")
                .excludePathPatterns("/captcha.jpg");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}