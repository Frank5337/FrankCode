package com.zbl.tank;

import java.io.IOException;
import java.util.Properties;
import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 15:47 2020/5/27
 * @Description:
 * @Version: $
 */
public class PropertyMgr {

    private static volatile PropertyMgr propertyMgr;

    private Properties properties = new Properties();

    private PropertyMgr() {

    }

    public static PropertyMgr getInstance() {
        if (propertyMgr == null) {
            synchronized (PropertyMgr.class) {
                if (propertyMgr == null) {
                    try {
                        propertyMgr = new PropertyMgr();
                        propertyMgr.properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return propertyMgr;
    }

    public static Object get(String key) {
        return getInstance().properties.get(key);
    }

    public static void main(String[] args) {
        System.out.println(get("gameHeight"));
        System.out.println(get("tankSpeed"));

        IntStream.rangeClosed(1,100).forEach(i -> new Thread(() -> {
            System.out.println(getInstance().properties.hashCode());
        }).start());

    }
}
