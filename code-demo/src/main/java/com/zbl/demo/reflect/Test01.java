package com.zbl.demo.reflect;

import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @Author: zbl
 * @Date: Created in 2022/2/25 15:44
 * @Description:
 * @Version: $
 */
public class Test01 {

    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setName("zbl5337");

        test(user, "name");

    }

    private static void test(Object user, String name) throws Exception {
        Field field = user.getClass().getDeclaredField(name);
        field.setAccessible(true);
        Object o = field.get(user);
//        Class<?> type = field.getType();
//        Object o1 = type.newInstance();
        System.out.println(o);
    }

    @Test
    public void test01() throws Exception {
        String name = "zbl5337${id}";
        String sub = "id";
        name = name.replace("${" + sub + "}", "%s");
        System.out.println(name);
    }

}

class User implements Serializable {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
