package com.zbl.test;

import com.zbl.KafkaSpringBootApplication;
import com.zbl.service.IMessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: zbl
 * @Date: Created in 2020/11/30
 * @Description:
 * @Version: $
 */
@SpringBootTest(classes = KafkaSpringBootApplication.class)
@RunWith(SpringRunner.class)
public class IMessageSenderTest {

    @Resource
    private IMessageSender iMessageSender;

    @Test
    public void iMessageSenderTest() throws Exception {
        iMessageSender.sendMessage("topic01", "imessageSender", "transactional IMessage senderTest");
    }


}
