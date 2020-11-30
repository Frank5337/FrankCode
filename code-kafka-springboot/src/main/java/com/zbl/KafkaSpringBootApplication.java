package com.zbl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.messaging.handler.annotation.SendTo;

import java.io.IOException;

/**
 * @Author: zbl
 * @Date: Created in 2020/11/30
 * @Description:
 * @Version: $
 */
@SpringBootApplication
public class KafkaSpringBootApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(KafkaSpringBootApplication.class, args);
        //因为没有使用web, 所以先read 挂起
        System.in.read();
    }

    @KafkaListeners(
            value = {
                    @KafkaListener(topics = {"topic01"})
            }
    )
    public void receive01(ConsumerRecord<String, String> consumerRecord) {
        System.out.println("record: " + consumerRecord);
    }

    /**
     * 接收02的数据发送给03
     */
    @KafkaListeners(
            value = {
                    @KafkaListener(topics = {"springbootSendTopic"})
            }
    )
    @SendTo("toTopic")
    public String receive02(ConsumerRecord<String, String> consumerRecord) {
        return consumerRecord.value() + " / com.zbl.kafka.springboot";
    }
}
