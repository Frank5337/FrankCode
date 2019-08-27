package com.zbl.code.common.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zbl
 * @Date: Created in 14:57 2019/8/27
 * @Description:
 * @Version: $
 */
@Configuration
@ComponentScan(basePackages = "com.zbl.code.common.conditional")
public class ConditionConfig {

    @Bean
    @Conditional(WindowsCondition.class)
    public ListService windowsListService() {
        return new WindowsListService();
    }

    @Bean
    @Conditional(LinuxCondition.class)
    public ListService linuxListService() {
        return new WindowsListService();
    }


}
