package com.zbl.service;

import com.zbl.pojo.User;

/**
 * @Author: zbl
 * @Date: 17:40 2020/3/1
 * @Description:
 */
public interface UserService {

    public User queryUserByName(String name);
}
