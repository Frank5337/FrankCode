package com.zbl.tomcat;

import java.io.OutputStream;

/**
 * @Author: zbl
 * @Date: 14:38 2020/6/21
 * @Description:
 */
public class MyResponse {

    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String str) throws Exception{
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HTTP/1.1 200 OK\n")
                .append("Content-Type:text/html\n")
                //响应体
                .append("\r\n")
                .append("<html>")
                .append("<body>")
                .append("<h1>")
                .append(str)
                .append("</h1>")
                .append("</body>")
                .append("</html>");
        this.outputStream.write(stringBuilder.toString().getBytes());
        this.outputStream.flush();
        this.outputStream.close();
    }
}
