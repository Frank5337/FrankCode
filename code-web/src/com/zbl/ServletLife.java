package com.zbl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zbl
 * @Date: Created in 11:50 2020/6/22
 * @Description: ${description}
 * @Version: $version$
 */
@WebServlet(name = "ServletLife")
public class ServletLife extends HttpServlet {

    /**
     * 在servlet第一次接收请求的时候 初始化
     * 若配置 <load-on-startup>1</load-on-startup>
     * 则服务器启动时初始化, 数值表示优先级
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        System.out.println("init");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("servlet life");
        System.out.println("servlet life");
    }

    /**
     * 服务器关闭的时候销毁
     */
    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
