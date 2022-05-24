package com.zbl.lpj.proxy.cjlib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyCglib implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("MyCglib start");
        Object o1 = methodProxy.invokeSuper(o, objects);
        System.out.println("MyCglib end");
        return o1;
    }
}