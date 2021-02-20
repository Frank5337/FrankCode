package com.zbl.filter.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/20 10:20
 * @Description:
 * @Version: $
 */
public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("编码过滤器的初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("编码过滤器的逻辑处理");
        //设置编码
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("编码过滤器的处理结束");
    }

    @Override
    public void destroy() {
        System.out.println("编码过滤器的销毁");
    }
}
