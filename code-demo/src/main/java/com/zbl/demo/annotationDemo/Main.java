package com.zbl.demo.annotationDemo;


import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @Author: zbl
 * @Date: Created in 2021/7/2
 * @Description: 自定义注解
 * @Version: $
 */
public class Main {

    @Test
    public void test01() throws Exception {
        Class clzz = Main.class;
        if (clzz.getMethod("viewManager").isAnnotationPresent(RequiredAdmin.class)) {
            System.out.println("需要权限登录");
        } else {
            System.out.println("不需要");
        }
        Method viewManager = clzz.getMethod("viewManager");
        RequiredAdmin requiredAdmin = viewManager.getAnnotation(RequiredAdmin.class);
        if (requiredAdmin != null) {
            String value = requiredAdmin.value();
            System.out.println(value);
        }

    }

//    @RequiredAdmin(value = "admin")
    @RequiredAdmin
    public void viewManager() {
        System.out.println("显示管理页面");
    }
}
