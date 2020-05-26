package com.zbl.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: zbl
 * @Date: 20:50 2020/5/25
 * @Description: RBAC 有状态的会话
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SsoSessionRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoSessionRedisApplication.class);
    }

}
