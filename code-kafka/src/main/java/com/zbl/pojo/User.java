package com.zbl.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: zbl
 * @Date: Created in 2020/11/25
 * @Description:
 * @Version: $
 */
public class User implements Serializable {

    private Long id;

    private String name;

    private Date birthday;

    public User(Long id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
