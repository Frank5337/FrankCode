package com.zbl.dds;

import com.zbl.pojo.User;
import com.zbl.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class DdsApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserService userService;

    @Test
    public void getHello() throws Exception {
        List<User> greeting = userService.findAll();
        System.out.println(greeting);
    }

    @Test
    public void userSet() throws Exception {
        userService.update(1L, false);
    }

}
