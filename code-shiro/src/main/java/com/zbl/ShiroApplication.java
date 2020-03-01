package com.zbl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: zbl
 * @Date: 15:32 2020/3/1
 * @Description:
 *
 * shiro的三大对象
 *    Subject 用户
 *    SecurityManager 管理所有用户
 *    Realm 连接数据
 */
//@SpringBootApplication(exclude={ DataSourceAutoConfiguration.class })
@SpringBootApplication
public class ShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class , args);
    }

}
