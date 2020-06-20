package com.zbl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author: zbl
 * @Date: 1:39 2020/6/21
 * @Description:
 */
public class MyResponse {

    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String str) throws IOException {
        StringBuilder buffer = new StringBuilder();
        //响应行
        buffer.append("HTTP/1.1 200 OK\n")
                //响应头
                .append("Context-Type:text/html\n")
                //响应体
                .append("\r\n")
                .append("<html>")
                .append("<body>")
                .append("<h1>")
                .append(str)
                .append("</h1>")
                .append("</body>")
                .append("</html>");
        this.outputStream.write(buffer.toString().getBytes());
        this.outputStream.flush();
        this.outputStream.close();
    }

}
