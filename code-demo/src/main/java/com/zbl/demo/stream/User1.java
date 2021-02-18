package com.zbl.stream;

import java.util.Objects;

/**
 * @Author: zbl
 * @Date: Created in 16:30 2020/3/6
 * @Description:
 * @Version: $
 */

public class User1 {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User1 user = (User1) o;
        return Objects.equals(Id, user.Id) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    private Integer Id;

    private String name;

    public User1(Integer id, String name) {
        Id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }
}
