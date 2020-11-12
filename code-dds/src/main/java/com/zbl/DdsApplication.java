package com.zbl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: zbl
 * @Date: Created in 2020/11/12
 * @Description: 读写分离, 动态数据源配置demo
 * @Version: $
 */
@SpringBootApplication
public class DdsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdsApplication.class, args);
    }

}
