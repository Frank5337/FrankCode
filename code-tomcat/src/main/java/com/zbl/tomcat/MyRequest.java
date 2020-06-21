package com.zbl.tomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: zbl
 * @Date: 14:38 2020/6/21
 * @Description:
 */
public class MyRequest {

    private String requestMethod;

    private String requestUrl;

    public MyRequest(InputStream inputStream) throws Exception {
        String str = "";
        byte[] buffer = new byte[1024];
        int len = 0;
        if ((len = inputStream.read(buffer)) > 0) {
            str = (new String(buffer, 0, len));
        }

        String data = str.split("\n")[0];
        String[] params = data.split(" ");
        if (params.length > 0)
            this.requestMethod = params[0];
        if (params.length > 1)
            this.requestUrl = params[1];
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
