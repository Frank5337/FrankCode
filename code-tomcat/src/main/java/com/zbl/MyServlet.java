package com.zbl;


/**
 * @Author: zbl
 * @Date: 2:30 2020/6/21
 * @Description:
 */
public class MyServlet extends MyHttpServlet {

    @Override
    public void doGet(MyRequest request, MyResponse response) throws Exception {
        response.write("My tomcat GET");
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) throws Exception{
        response.write("My tomcat POST");
    }
}
