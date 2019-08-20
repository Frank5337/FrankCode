package com.zbl.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: zbl
 * @Date: Created in 10:49 2019/8/20
 * @Description:
 * @Version: $
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableTransactionManagement
@ServletComponentScan
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
