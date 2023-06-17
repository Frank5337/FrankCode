package com.zbl.demo.record;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Frank
 * @date: Created in 2023/6/17
 */
public class Main {

    public static void main(String[] args) throws Exception{
        User user = new User();
        user.setFirstName("路遥");
        List<String> changeFiled = new ArrayList<>();

        // 获取类的所有字段
        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // 设置字段可访问

            try {
                Object value = field.get(user);
                if (value == null) {
                    System.out.println(field.getName() + " is null");
                } else {
                    changeFiled.add(field.getName());
                    System.out.println(field.getName() + " is not null");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println(changeFiled.toString());
    }
}
