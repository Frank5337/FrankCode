package com.zbl.code.common.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 *
 * @author jlf
 * @date 2018/12/11
 * @since v1.0
 */
@Component
public class DistributionLock {
    
    @Autowired
    StringRedisTemplate redisTemplate;
    
    /**
     * 自动刷新缓存时getExpire得到的值
     */
    private final static Long UNLIMITED = -1L;
    
    /**
     * 加锁
     *
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public boolean lock(String key, long timeout, TimeUnit unit) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            
            @Override
            public Boolean doInRedis(RedisConnection c) throws DataAccessException {
                StringRedisConnection connection = (StringRedisConnection) c;
                Boolean aTrue = connection.setNX(key, "true");
                // 值为false,说明此前已加了锁,加锁失败
                if (!aTrue) {
                    // 防止死锁,当加锁失败时,判断锁的超时时间,若为-2,主动设置一下超时时间
                    Long expire = connection.ttl(key);
                    if (UNLIMITED.equals(expire)) {
                        connection.setEx(key, unit.toSeconds(timeout), "true");
                    }
                    return false;
                }
                // 锁超时时间10秒
                connection.setEx(key, unit.toSeconds(timeout), "true");
                return true;
            }
        });
        
    }

    /*
    * 自旋锁
    * @author wanQ
    * */
    public void spinLock(String key, long timeout, TimeUnit unit){

        boolean flag = lock(key, timeout, unit);
        while (!flag) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            flag = lock(key, timeout, unit);
        }

    }
    
    /**
     * 解锁
     *
     * @param key
     * @return
     */
    public boolean unLock(String key) {
        redisTemplate.delete(key);
        return true;
    }
    
}
