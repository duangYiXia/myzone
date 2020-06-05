package com.xxkj.resume.resume.filter;

import com.xxkj.resume.resume.common.CommonConstant;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zjx
 * @create 2020/3/20 10:31
 */
@Component
@Order(1)
public class IndexFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();

        if(CommonConstant.ROOT_PATH.equals(uri)) {
            response.sendRedirect("/hello");
        }
        filterChain.doFilter(request, response);
    }
}
