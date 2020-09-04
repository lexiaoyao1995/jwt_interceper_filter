package com.interceptortest.demo.filter;

import com.interceptortest.demo.component.TestComponent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")//匹配的url路径
public class AppFilter implements Filter {

    /**
     * 通过WebFilter配置拦截器，可以注入成员
     */
    @Autowired
    private TestComponent testComponent;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("拦截器init");
    }

    /**
     * 拦截请求
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String method = testComponent.method();
        System.out.println("过滤器");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 程序终止的时候调用
     */
    @Override
    public void destroy() {
        System.out.println("拦截器destroy");
    }
}
