package com.zbl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: 1:19 2020/6/21
 * @Description:
 */
public class MyRequest {

    //请求方法 get post
    private String requestMethod;

    private String requestUrl;

    public MyRequest(InputStream inputStream) throws IOException {
        //缓冲区域
        byte[] buffer = new byte[1024];
        //读取数据的长度;
        int len = 0;
        //定义请求的变量
        String str = "";
        if ((len = inputStream.read(buffer)) > 0) {
            str = new String(buffer, 0, len);
        }
        //GET / HTTP/1.1
        String data = str.split("\n")[0];
        String[] params = data.split(" ");
        for (String param : params) {
            System.out.print(param);
            System.out.print(",");
        }
        if (params.length > 0) {
            this.requestMethod = params[0];
        }
        if (params.length > 1) {
            this.requestUrl = params[1];
        }
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }


}
