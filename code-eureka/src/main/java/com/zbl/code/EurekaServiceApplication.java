package com.zbl.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: zbl
 * @Date: 14:57 2019/10/27
 * @Description:
 */
@EnableEurekaServer
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EurekaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceApplication.class, args);
        System.out.println("注册中心启动成功");
    }
}
