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
 * @Date: Created in 2021/2/19 17:11
 * @Description:
 * @Version: $
 *
 * 自定义过滤器
 *      完成请求的拦截以及资源的统一管理功能
 *      使用方式
 *          定义普通Java类, 实现filter接口
 *          重写其中方法
 *              init , 完成初始化功能   tomcat启动的时候执行一次
 *              doFilter , 进行处理    每次发送请求都会执行
 *              destory , 进行销毁     tomcat关闭的时候
 */
public class MyFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("我是filter2 init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("我是filter2 逻辑处理");
        //添加此语句之后才能调用到对应的servlet  servlet doGet/doPost
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("我是filter2 逻辑完成");
    }

    @Override
    public void destroy() {
        System.out.println("我是filter2 销毁功能");
    }
}
