package com.zbl;

import com.zbl.controller.MyController;
import com.zbl.pojo.User;
import com.zbl.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 15:17 2020/5/27
 * @Description:
 * @Version: $
 */
//启动Spring
@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringBootTest {

    @Autowired
    private MyController myController;

    @Autowired
    private UserService userService;

    @Test
    public void getHello() throws Exception {
        final List<User> greeting = myController.list();
        System.out.println(greeting);
        Assert.assertNotNull(greeting);
    }

    @Test
    public void userSet() throws Exception {
//        userService.updateIsSystem(1L, false);
        userService.updatePop(1L, false);
    }
}
