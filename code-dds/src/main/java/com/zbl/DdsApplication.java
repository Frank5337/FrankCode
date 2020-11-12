package com.zbl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 读写分离, 动态数据源配置demo
 */
@SpringBootApplication
public class DdsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdsApplication.class, args);
    }

}
