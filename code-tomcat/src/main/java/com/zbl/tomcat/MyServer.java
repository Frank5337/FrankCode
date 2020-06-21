package com.zbl.tomcat;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: zbl
 * @Date: 14:49 2020/6/21
 * @Description:
 */
public class MyServer {

    public static void startServer(int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);

        Socket socket = null;

        for (;;) {
            socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            MyRequest request = new MyRequest(inputStream);
            MyResponse response = new MyResponse(outputStream);
            String clazz = new MyMapping().getMapping().get(request.getRequestUrl());
            if (clazz != null) {
                Class<MyServlet> aClass = (Class<MyServlet>) Class.forName(clazz);
                MyServlet myServlet = aClass.newInstance();
                myServlet.service(request, response);
            }

        }
    }

    public static void main(String[] args) throws Exception{
        startServer(10087);
    }
}
