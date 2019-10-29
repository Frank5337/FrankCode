package com.zbl.code.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author: zbl
 * @Date: Created in 14:39 2019/8/20
 * @Description: Session配置
 * @Version: $
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 36000)
public class SessionConfig {
    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }
}
