package com.interceptortest.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class TestIntercepter extends HandlerInterceptorAdapter {

    @Autowired
    private TestComponent testComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        registry.addInterceptor(new TestIntercepter()).addPathPatterns("/test/**");
//        如果是采用new的方式注入，则Autowired的字段不会生效，需要重新在上下文中获取
        if (testComponent == null) {
            WebApplicationContext requiredWebApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            testComponent = (TestComponent) requiredWebApplicationContext.getBean("testComponent");
        }

        HandlerMethod method = (HandlerMethod) handler;
        Anno loginRequired = method.getMethodAnnotation(Anno.class);
        if (Objects.isNull(loginRequired)) {
            System.out.println("无需拦截");
            return true;
        } else {
            System.out.println("通过注解拦截");
            return false;
        }
    }
}
