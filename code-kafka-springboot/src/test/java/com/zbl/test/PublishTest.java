package com.zbl.test;

import com.zbl.KafkaSpringBootApplication;
import com.zbl.publish.ContentEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: zbl
 * @Date: Created in 2021/8/26 15:47
 * @Description:
 * @Version: $
 */
@SpringBootTest(classes = KafkaSpringBootApplication.class)
@RunWith(SpringRunner.class)
public class PublishTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test01() throws Exception {
        applicationContext.publishEvent(new ContentEvent("今天是8月26日, 晚上不用加班"));
    }

}
