package com.zbl.tomcat;

/**
 * @Author: zbl
 * @Date: 14:36 2020/6/21
 * @Description:
 */
public abstract class MyHttpServlet {

    private static final String METHOD_GET = "GET";

    private static final String METHOD_POST = "POST";

    public abstract void doGet(MyRequest request, MyResponse response) throws Exception;

    public abstract void doPost(MyRequest request, MyResponse response) throws Exception;

    public void service(MyRequest request, MyResponse response) throws Exception {
        if (METHOD_GET.equals(request.getRequestMethod())) {
            doGet(request, response);
        }
        if (METHOD_POST.equals(request.getRequestMethod())) {
            doPost(request, response);
        }
    }


}
