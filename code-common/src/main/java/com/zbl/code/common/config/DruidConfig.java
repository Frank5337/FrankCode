package com.zbl.code.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @Author: zbl
 * @Date: Created in 15:31 2019/8/20
 * @Description:
 * @Version: $
 */
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSource() {
        return new DruidDataSource();
    }
}
