package com.zbl.tomcat;

/**
 * @Author: zbl
 * @Date: 14:46 2020/6/21
 * @Description:
 */
public class MyServlet extends MyHttpServlet{


    @Override
    public void doGet(MyRequest request, MyResponse response) throws Exception{
        response.write("get");
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) throws Exception{
        response.write("post");
    }
}
