package com.zbl.controller;


import com.zbl.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author: zbl
 * @Date: 15:30 2020/3/1
 * @Description:
 */
@Controller
public class MyController {

    @Resource
    private UserService userService;

    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {
        //System.out.println(userService.queryUserByName("root"));
        model.addAttribute("msg", "hello world");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model) {
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token); //执行登录的方法, 如果没有异常 就说明ok了
            return "index";
        } catch (UnknownAccountException e) {
            //用户名不存在
            model.addAttribute("msg", "用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e) {
            //密码错误
            model.addAttribute("msg", "密码错误");
            return "login";
        }

    }

    @RequestMapping("/unAuth")
    @ResponseBody
    public String unAuthorized(){
        return "未经授权无法访问此页面";
    }
}
