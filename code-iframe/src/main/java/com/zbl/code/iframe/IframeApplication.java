package com.zbl.code.iframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class IframeApplication {

    public static void main(String[] args) {
        SpringApplication.run(IframeApplication.class, args);
    }

}
