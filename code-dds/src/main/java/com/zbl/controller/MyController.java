package com.zbl.controller;


import com.zbl.pojo.User;
import com.zbl.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: zbl
 * @Date: 15:30 2020/3/1
 * @Description:
 */
@Controller
@Slf4j
public class MyController {

    @Resource
    private UserService userService;

    @RequestMapping("/list")
    @ResponseBody
    public List<User> list(){
        List<User> list = userService.findAll();
        log.info("list = {}", list);
        return list;
    }

    @RequestMapping("/set")
    @ResponseBody
    public void set() throws IllegalAccessException {
        userService.update(1L, false);
    }



}
