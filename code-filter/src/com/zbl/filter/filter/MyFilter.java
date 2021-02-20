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
 *
 *     <filter>
 *         <filter-name>MyFilter</filter-name>                           别名
 *         <filter-class>com.zbl.filter.filter.MyFilter</filter-class>   类名
 *     </filter>
 *
 *     <filter-mapping>
 *         <filter-name>MyFilter</filter-name>                           别名
 *         <url-pattern>/*</url-pattern>                                 表示要匹配的请求
 *     </filter-mapping>
 *
 *      filterChain 表示过滤器链
 *          在项目中可用定义N多个过滤器, 根据用户的请求把符合规则的过滤器挨个执行
 *          建议, 每个过滤器完成独立的功能, 不要将所有的逻辑处理放置到同一个过滤器中, 耦合性高, 不利于维护
 *
 *          在过滤器的web.xml配置文件中, 可以指定过滤器过滤哪些请求,
 *          /* 匹配所有请求
 *          /*.do 匹配所有后缀为.do的请求
 *          /filter.do 匹配请求为filter.do的请求
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("我是filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("我是filter 逻辑处理");
        //添加此语句之后才能调用到对应的servlet  servlet doGet/doPost
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("我是filter 逻辑完成");
    }

    @Override
    public void destroy() {
        System.out.println("我是filter 销毁功能");
    }
}
