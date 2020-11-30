package com.zbl.service.impl;

import com.zbl.service.IMessageSender;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: zbl
 * @Date: Created in 2020/11/30
 * @Description: kafka支持spring的transactional注解
 * @Version: $
 */
@Service
@Transactional
public class IMessageSenderImpl implements IMessageSender {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(String topic, String key, String message) {
        kafkaTemplate.send(new ProducerRecord<>(topic, key, message));
    }
}
