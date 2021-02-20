package com.zbl.filter.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/20 10:54
 * @Description:
 * @Version: $
 * <p>
 * 监听器:
 * 监听作用域对象的创建和销毁以及属性的相关配置, 可以添加一些公共属性配置, 做逻辑判断
 * <p>
 * 主要监听3种作用域 servletContext, session, request
 * request
 * 接口
 * ServletRequestListener                  监听request的创建销毁
 * ServletRequestAttributeListener         监听request作用域属性的添加删除和更改
 * <p>
 * servletContext
 * 接口
 * ServletContextListener                  监听servlet的创建与销毁    tomcat启动时创建的, tomcat关闭时销毁
 * ServletContextAttributeListener         监听ServletContext作用域属性的添加删除和更改
 * <p>
 * HttpSessionListener                     监听session创建与销毁
 * HttpSessionAttributeListener            监听sessionAttribute的添加删除更改
 */
public class MyListener implements ServletRequestListener,
        ServletRequestAttributeListener, ServletContextListener, ServletContextAttributeListener,
        HttpSessionListener, HttpSessionAttributeListener {

    private volatile static Integer onlineNum = 0;

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("servletRequest被销毁");
        String listener = (String) servletRequestEvent.getServletRequest().getAttribute("listener");
        System.out.println(listener);
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println("servletRequest被创建");
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.print("attributeAdded---");
        System.out.println("N:" + servletRequestAttributeEvent.getName() + " v:" + servletRequestAttributeEvent.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.print("attributeRemoved---");
        System.out.println("N:" + servletRequestAttributeEvent.getName() + " v:" + servletRequestAttributeEvent.getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.print("attributeReplaced---");
        System.out.println("N:" + servletRequestAttributeEvent.getName() + " v:" + servletRequestAttributeEvent.getValue());
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext sc = servletContextEvent.getServletContext();
        sc.setAttribute("count", 0);
        System.out.println("servletContext创建");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("servletContext销毁");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("servletContext attributeAdded");
        System.out.println(servletContextAttributeEvent.getName());
        System.out.println(servletContextAttributeEvent.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("servletContext attributeRemoved");
        System.out.println(servletContextAttributeEvent.getName());
        System.out.println(servletContextAttributeEvent.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("servletContext attributeReplaced");
        System.out.println(servletContextAttributeEvent.getName());
        System.out.println(servletContextAttributeEvent.getValue());
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ServletContext sc = httpSessionEvent.getSession().getServletContext();
        sc.setAttribute("count", (Integer) sc.getAttribute("count") + 1);
        System.out.println("session 被创建");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ServletContext sc = httpSessionEvent.getSession().getServletContext();
        sc.setAttribute("count", (Integer) sc.getAttribute("count") - 1);
        System.out.println("session 被销毁");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
