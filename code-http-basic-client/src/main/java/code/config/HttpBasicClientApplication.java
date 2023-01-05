package code.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: zbl
 * @Date: 14:57 2019/10/27
 * @Description:
 */
@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class HttpBasicClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(HttpBasicClientApplication.class, args);
        System.out.println("run success");
    }
}
