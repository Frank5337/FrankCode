package com.zbl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zbl
 * @Date: 18:58 2020/6/21
 * @Description:
 * 无论get 还是 post 都会走service()
 * 没有service post请求调用post get -> get
 * 如果没有 405, 不支持xx请求
 */
public class MyServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("web2 project...............");
        System.out.println("my web2 project");
        super.service(req, resp);
    }
}
