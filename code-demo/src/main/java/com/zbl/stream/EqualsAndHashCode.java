package com.zbl.stream;

/**
 * @Author: zbl
 * @Date: Created in 11:50 2020/4/10
 * @Description:
 * @Version: $
 */
public class EqualsAndHashCode {

    public static void main(String[] args) {
        User user = new User(1, "zbl");
        System.out.println(user.hashCode());
        User user1 = new User(1, "zbl");
        System.out.println(user1.hashCode());

    }
}
