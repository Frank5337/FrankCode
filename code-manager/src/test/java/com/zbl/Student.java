package com.zbl;

import lombok.Data;

/**
 * @Author: zbl
 * @Date: Created in 10:32 2019/12/16
 * @Description:
 * @Version: $
 */
@Data
public class Student {

    private Integer id;
    private String name;
    private Integer age;
    private Gender gender;

    public Student(Integer id, String name, Integer age, Gender gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
