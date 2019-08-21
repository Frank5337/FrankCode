package com.zbl.code.common.base;

import org.springframework.data.redis.cache.RedisCache;

import javax.annotation.Resource;

/**
 * @Author: zbl
 * @Date: Created in 11:08 2019/8/21
 * @Description: 通用服务
 * @Version: $
 */
public class BaseService {
    @Resource
    private RedisCache redisCache;
}
