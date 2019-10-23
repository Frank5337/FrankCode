package com.zbl.code.common.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Created by hook on 2018/7/25.
 * <p>
 * Redis缓存封装（包括常用的put，get）
 */
@Slf4j
@Component
@SuppressWarnings("unchecked")
public class RedisCache {
    @Resource
    private RedisTemplate redisTemplate;

    @SuppressWarnings("unchecked")
    public void put(String key, Object val) {
        try {
            redisTemplate.opsForValue().set(key, val);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void put(String key, Object val, Integer time) {
        try {
            redisTemplate.opsForValue().set(key, val, time, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void del(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public Object get(String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * redis SetNx 指令
     */
    private boolean setNx(String key, Object val) {
        return stringRedisTemplate.execute((RedisConnection conn) -> {
            try {
                return conn.setNX(stringRedisTemplate.getStringSerializer().serialize(key) ,
                        stringRedisTemplate.getStringSerializer().serialize(val.toString()));
            } finally {
                conn.close();
            }
        });
    }

    private void setNx(String key, Object val, long time) {
        
    }
}