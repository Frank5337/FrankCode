package com.zbl.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: zbl
 * @Date: Created in 15:47 2020/5/27
 * @Description:
 * @Version: $
 */
public class PropertyMgr {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        return properties == null ? null : properties.get(key);
    }

    public static void main(String[] args) {
        System.out.println(get("gameHeight"));
        System.out.println(get("tankSpeed"));
    }
}
