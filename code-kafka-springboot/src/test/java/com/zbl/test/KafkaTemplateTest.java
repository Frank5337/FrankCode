package com.zbl.test;

import com.zbl.KafkaSpringBootApplication;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
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
public class KafkaTemplateTest {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 非事务场景
     *
     * @throws Exception
     */
    @Test
    public void testSendMessage() throws Exception {
        kafkaTemplate.send(new ProducerRecord<>("topic02", "test", "testSendMessage"));
    }

    /**
     * 事务场景
     *
     * @throws Exception
     */
    @Test
    public void testSendMessageTransactional() throws Exception {
        kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback<String, String, Object>() {
            @Override
            public Object doInOperations(KafkaOperations<String, String> kafkaOperations) {
                kafkaOperations.send(new ProducerRecord<>("testccc",
                        "testTransactional", "testSendMessage transactional"));
                return null;
            }
        });
    }

}
