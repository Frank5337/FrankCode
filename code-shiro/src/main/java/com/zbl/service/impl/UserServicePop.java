package com.zbl.service.impl;

import com.zbl.mapper.UserMapper;
import com.zbl.pojo.User;
import com.zbl.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: zbl
 * @Date: 17:40 2020/3/1
 * @Description:
 */
@Service
public class UserServicePop {

    @Resource
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void update(Long userId, boolean isSystem) throws IllegalAccessException {
        //userMapper.findById(userId);
        userMapper.updateIsSystem(userId, isSystem);
        if (userId.equals(1L)) {
            //throw new NullPointerException();//可回滚, 继承了RuntimeExp
            //throw new IllegalAccessException();//不会被回滚的, 已检查的, 需要@Transactional(rollbackFor = Exception.class)
        }
        //int i = 1/0;
    }

}
