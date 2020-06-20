package com.zbl;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: zbl
 * @Date: 2:34 2020/6/21
 * @Description:
 */
public class MyServer {

    /**
     * 定义服务端的接收程序, 接收socket请求
     *
     * @param port 端口号
     */
    public static void startServer(int port) throws Exception {

        //定义服务端套接字
        ServerSocket serverSocket = new ServerSocket(port);

        //定义客户端套接字
        Socket socket = null;

        for (; ; ) {
            //接收
            socket = serverSocket.accept();
            //获取输入流和输出流
            InputStream inputStream = socket.getInputStream();

            OutputStream outputStream = socket.getOutputStream();

            //定义请求对象
            MyRequest request = new MyRequest(inputStream);
            //定义响应对象
            MyResponse response = new MyResponse(outputStream);

            String clazz = new MyMapping().getMapping().get(request.getRequestUrl());
            if (clazz != null) {
                Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(clazz);
                //根据myServletClass创建对象
                MyServlet myServlet = myServletClass.newInstance();
                myServlet.service(request, response);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        startServer(10086);
    }

}
