package com.zbl.demo;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @Author: zbl
 * @Date: Created in 2022/3/11 11:33
 * @Description:
 * @Version: $
 */

public class BaseAttribute {

    String appName;

    String appHash;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppHash() {
        return appHash;
    }

    public void setAppHash(String appHash) {
        this.appHash = appHash;
    }

    public static void main(String[] args) {
        String json = "{\"appName\":\"plsql\",\"appHash\":\"aaaaaa\"}";
        BaseAttribute baseAttribute = JSONObject.parseObject(json, BaseAttribute.class);
        System.out.println(baseAttribute.getAppName());
        System.out.println(baseAttribute.getAppHash());
    }

    @Test
    public void test01() throws Exception {
        System.out.println(t());
    }

    private static String t() {
        int x = 0;
        try {

            System.out.println("try");
            x++;
            return "return try" + x;
        } catch (Exception e) {

        } finally {
            x++;
            System.out.println("finally" + x);
//            return "fff";
        }
        return "return" + x;
    }
}
