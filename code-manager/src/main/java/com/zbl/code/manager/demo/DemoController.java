package com.zbl.code.manager.demo;

import com.zbl.code.common.component.RedisCache;
import com.zbl.code.common.component.RedisDistributionLock;
import com.zbl.code.common.pojo.JsonResult;
import com.zbl.code.common.util.TimeUtil;
import io.swagger.annotations.Api;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Date;

/**
 * @Author: zbl
 * @Date: 16:15 2020/4/5
 * @Description:
 */
@RestController
@RequestMapping("/Demo")
@Api(tags = "01.Demo-01: test")
public class DemoController {


    @Resource
    private DataSource dataSource;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedisCache redisCache;

    @Resource
    private RedisDistributionLock redisDistributionLock;

    @PostMapping("lock")
    public String lock(){
//        boolean lock = redisDistributionLock.lock("frozen", 1000 * 10);
//        Date date = new Date();
//        date.setTime(System.currentTimeMillis());
//        return lock ? "lock success" : "lock fail" + " now: " + TimeUtil.date2String(date, "yyyy:MM:dd HH:mm:ss");
        //自旋
        redisDistributionLock.spinLock("frozen", 1000 * 10);
        return "";
    }

    @GetMapping("getLock")
    public String getLock(){
        String f = (String) redisCache.get("frozen");
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        System.out.println("当前：" + TimeUtil.date2String(date, "yyyy:MM:dd HH:mm:ss"));
        if (f != null) {
            System.out.println("lock：" + TimeUtil.date2String(date, "yyyy:MM:dd HH:mm:ss"));
        }
        return f;
    }

    @GetMapping("unlock")
    public Boolean unlock(){
        return redisDistributionLock.unlock("frozen");
    }

}

