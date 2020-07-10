package com.mashibing.admin;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {


    @RequestMapping(value = "/sse", produces = "text/event-stream;charset=utf-8")
    public Object xxoo() {

        System.out.println("来啦 老弟！" + Thread.currentThread().getName());

        Date date = new Date();
        return "data:" + date.getTime() + " \n\n";
    }


}
