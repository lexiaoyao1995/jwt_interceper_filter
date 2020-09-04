package com.interceptortest.demo.config;


import com.interceptortest.demo.component.AppIntercepter;
import com.interceptortest.demo.component.TestIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

    @Autowired
    private AppIntercepter appIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //采用注入的方式配置，则Intercepter中可以自动注入成员变量
        registry.addInterceptor(appIntercepter).addPathPatterns("/app/**");
        //如果是采用new的方式注入，则Intercepter中不能自动注入成员变量，需要手动获取
        registry.addInterceptor(new TestIntercepter()).addPathPatterns("/test/**");
    }

    /**
     * 跨域支持
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS", "HEAD")
                .maxAge(3600 * 24);
    }
}
