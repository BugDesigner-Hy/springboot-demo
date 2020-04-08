package com.haiyang.security.config.filter;/**
 * @Author: HaiYang
 * @Date: 2019/10/30 11:35
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Administrator
 * @Date: 2019/10/30 11:35
 * @Description:
 */
@Slf4j
@WebFilter(filterName = "customFilter", urlPatterns = {"/private/**"})
@Component
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig){
        ServletContext servletContext = filterConfig.getServletContext();
        log.info("Init ServerInfo:{} ", servletContext.getServerInfo());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUrl = request.getRequestURL().toString();
        log.info("requestUrl:{},status:{}",requestUrl,response.getStatus());
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("destroy filter");
    }
}
