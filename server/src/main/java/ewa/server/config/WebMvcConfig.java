package ewa.server.config;

import ewa.server.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new RequestInterceptor())
                .excludePathPatterns("/api/auth/register", "/api/auth/login", "/api/pokemon/image/*")
                .addPathPatterns("/api/**");
    }
}
