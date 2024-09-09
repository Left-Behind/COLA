package com.azhu.apocalypse.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Slf4j
@Component
public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化过滤器
        log.info("TestFilter init ");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        log.info("Request URI start: " + httpRequest.getRequestURI());

        // 继续调用过滤器链
        chain.doFilter(request, response);
        log.info("Request URI start: " + httpRequest.getRequestURI());
    }

    @Override
    public void destroy() {
        // 销毁过滤器
        log.info("TestFilter destroy");
    }
}
