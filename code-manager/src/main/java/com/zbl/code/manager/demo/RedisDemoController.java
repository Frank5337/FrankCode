package com.zbl.code.manager.demo;

import io.swagger.annotations.Api;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: zbl
 * @Date: Created in 2023/1/6 15:07
 * @Description:
 * @Version: $
 */
@RestController
@RequestMapping("/redis")
@Api(tags = "02.RedisDemo-01: test")
public class RedisDemoController {

    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("/demo1")
    @Transactional
    public Long demo1() {
        return redisTemplate.opsForValue().increment("count", 1);
    }

    @PostMapping("/demo2")
    @Transactional
    public Long demo2() {
        redisTemplate.setEnableTransactionSupport(true);
        Long res = redisTemplate.opsForValue().increment("count", 1);
        redisTemplate.setEnableTransactionSupport(false);
        return res;
    }

    /**
     * Redis Multi 命令用于标记一个事务块的开始，事务块内的多条命令会按照先后顺序被放进一个队列当中，最后由 EXEC 命令原子性(atomic)地执行。
     * 手动事务
     * 但是这种写法有个弊端，如果在执行 Redis 事务期间，在 @Transactional 注解的方法里面执行 Redis 命令，则还是会造成返回结果为 null
     */
    @PostMapping("/demo3")
    public Long demo3() {
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.multi();
        Long res = redisTemplate.opsForValue().increment("count", 1);
        redisTemplate.exec();
        redisTemplate.setEnableTransactionSupport(false);
        return res;
    }

}
