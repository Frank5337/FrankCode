package com.zbl.transactional;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.record.TimestampType;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

/**
 * @Author: zbl
 * @Date: Create in 2020/11/23
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class KafkaConsumerTransactionalReadUnCommitted {
    public static void main(String[] args) {
        //1.创建KafkaConsumer
        //泛型, 发送的record 的key类型和值类型
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "144-kafka-a:9092,145-kafka-b:9092,146-kafka-c:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "t2");

        //默认是 read_uncommitted
        //设置消费者的消费事务的隔离级别 read_committed
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_uncommitted");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        //2.订阅相关的topic
        consumer.subscribe(Arrays.asList("topic01"));

        //遍历消息队列
        while (true) {
            //1秒去看1次
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            //取到了数据
            if (!consumerRecords.isEmpty()) {
                Iterator<ConsumerRecord<String, String>> recordIterator = consumerRecords.iterator();
                while (recordIterator.hasNext()) {
                    ConsumerRecord<String, String> record = recordIterator.next();
                    String topic = record.topic();
                    int partition = record.partition();
                    long offset = record.offset();
                    String key = record.key();
                    String value = record.value();
                    long timestamp = record.timestamp();
                    TimestampType type = record.timestampType();
                    System.out.println(topic+"\t" + partition + ", "+offset+"\t" + key + " " + value + " " + timestamp +" " + type);
                }
            }
        }

    }
}
