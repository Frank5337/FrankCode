package com.zbl.code;

import com.zbl.controller.MyController;
import com.zbl.pojo.User;
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
@RunWith(SpringRunner.class)
//启动Spring
@SpringBootTest
public class MySpringBootTest {

    @Autowired
    private MyController myController;

    @Test
    public void getHello() throws Exception {
        final List<User> greeting = myController.list();
        System.out.println(greeting);
        Assert.assertNotNull(greeting);
    }
}
