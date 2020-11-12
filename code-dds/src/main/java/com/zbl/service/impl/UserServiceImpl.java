package com.zbl.service.impl;


import com.zbl.mapper.UserMapper;
import com.zbl.pojo.User;
import com.zbl.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void updateIsSystem(Long userId, boolean isSystem) throws IllegalAccessException {
        update(userId, isSystem);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(Long userId, boolean isSystem) throws IllegalAccessException {
        userMapper.updateIsSystem(userId, isSystem);
    }
}
