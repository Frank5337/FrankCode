package com.zbl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zbl
 * @Date: 20:45 2020/5/25
 * @Description:
 */

@RestController
public class DemoController {

    @GetMapping("/demo01")
    public String demo01(HttpServletRequest request) {
        request.getSession().setAttribute("xijiao", "xo");
        return "xxoo";
    }

    @GetMapping("/demo02")
    public String demo02(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("xijiao");
    }
}
