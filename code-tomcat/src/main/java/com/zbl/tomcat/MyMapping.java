package com.zbl.tomcat;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zbl
 * @Date: 14:48 2020/6/21
 * @Description:
 */
public class MyMapping {

    private static Map<String, String> mapping = new HashMap<>();

    static {
        mapping.put("/myTomcat", "com.zbl.tomcat.MyServlet");
    }

    public static Map<String, String> getMapping() {
        return mapping;
    }
}
