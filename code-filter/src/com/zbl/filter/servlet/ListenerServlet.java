package com.zbl.filter.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/19 16:44
 * @Description:
 * @Version: $
 */
public class ListenerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        System.out.println("我是ListenerServlet");
        req.setAttribute("listener", "listener");

        ServletContext sc = this.getServletContext();
        sc.setAttribute("servletContext", "scv");

        HttpSession session = req.getSession();
        session.setAttribute("sessionTest", "frozen");
        session.invalidate();
        resp.getWriter().write("ListenerServlet succ");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
    //84920261
}
