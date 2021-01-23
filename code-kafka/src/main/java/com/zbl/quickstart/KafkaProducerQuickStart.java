package com.zbl.quickstart;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Author: zbl
 * @Date: Create in 2020/11/23
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class KafkaProducerQuickStart {
    public static void main(String[] args) throws InterruptedException {
        //1.创建KafkaProducer
        //泛型, 发送的record 的key类型和值类型
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                "144-kafka-a:9092,145-kafka-b:9092,146-kafka-c:9092");
                "8.136.133.137:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 5; i++) {
            Thread.sleep(500);
            ProducerRecord<String, String> record = new ProducerRecord<>("zblkafka", 0,"key:" + i, "value:" + i);
            // 没有key 就是轮询的
            //            ProducerRecord<String, String> record = new ProducerRecord<>("topic01",  "value:" + i);
            //发送给消息服务器
            producer.send(record);
        }

        //关闭生产者
        producer.close();
    }
}
