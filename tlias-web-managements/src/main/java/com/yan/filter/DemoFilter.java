package com.yan.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
@Slf4j
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { //web服务器启动自动调用,只调用一次
        log.info("初始化方法...");
    }

    //    销毁方法,web服务器关闭执行,只执行一次
    @Override
    public void destroy() {
        log.info("destroy销毁方法...");

    }

    //拦截到请求后执行,执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("拦截到了请求...");
//        放行
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
