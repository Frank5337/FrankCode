package com.zbl.code;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: zbl
 * @Date: Created in 9:06 2019/8/20
 * @Description:
 * @Version: $
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableCaching
@ServletComponentScan
@EnableEurekaClient
@EnableFeignClients
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    /**
     * Long 类型转为字符串
     *
     * @return Jackson2ObjectMapperBuilderCustomizer
     */
    @Bean("jackson2ObjectMapperBuilderCustomizer")
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.serializerByType(Long.class,
                ToStringSerializer.instance).serializerByType(Long.TYPE, ToStringSerializer.instance);
    }

}
