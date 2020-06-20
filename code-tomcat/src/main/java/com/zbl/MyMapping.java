package com.zbl;

import java.util.HashMap;

/**
 * @Author: zbl
 * @Date: 1:44 2020/6/21
 * @Description:
 */
public class MyMapping {

    private static HashMap<String, String> mapping = new HashMap<>();

    static {
        mapping.put("/myTomcat", "com.zbl.MyServlet");
    }

    public HashMap<String, String> getMapping() {
        return mapping;
    }


}
