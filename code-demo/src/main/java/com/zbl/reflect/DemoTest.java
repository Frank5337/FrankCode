package com.zbl.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Created in 15:03 2020/3/10
 * @Description:
 * @Version: $
 */
public class DemoTest {

    public static void main(String[] args) throws Exception {
        //MyProxy m = new MyProxy();
        Class c = MyProxy.class;
        Constructor[] constructors = c.getDeclaredConstructors();
        System.out.println(Arrays.toString(constructors));
        MyProxy o = (MyProxy) c.newInstance();
        Field field = c.getDeclaredField("name");
        field.setAccessible(true);
        field.set(o, "test");
        System.out.println(o.getName());

    }

}

class MyProxy {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}