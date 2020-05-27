package com.zbl.service.impl;

import com.zbl.mapper.UserMapper;
import com.zbl.pojo.User;
import com.zbl.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: zbl
 * @Date: 17:40 2020/3/1
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void updateIsSystem(Long userId, boolean isSystem) {
        userMapper.updateIsSystem(userId, isSystem);
    }
}
