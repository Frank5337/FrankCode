package com.zbl.freedom.annotation;


import org.junit.Test;

public class Main {

    @Test
    public void test01() throws Exception {
        Class clzz = Main.class;
        if (clzz.getMethod("viewManager").isAnnotationPresent(RequiredAdmin.class)) {
            System.out.println("需要权限登录");
        } else {
            System.out.println("不需要");
        }

    }

    @RequiredAdmin
    public void viewManager() {
        System.out.println("显示管理页面");
    }
}
