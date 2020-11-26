package com.zbl.offsets;

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
import java.util.regex.Pattern;

/**
 * @Author: zbl
 * @Date: Create in 2020/11/23
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class KafkaConsumerOffSet01 {
    public static void main(String[] args) {
        //1.创建KafkaConsumer
        //泛型, 发送的record 的key类型和值类型
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "144-kafka-a:9092,145-kafka-b:9092,146-kafka-c:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "g1");

        //默认配置是latest 从最新的开始去读, 比如 提供者先发送 然后再启动消费者 是不会消费之前的信息的
        //没有偏移量的时候, 系统会读取最新的offset作为读取的位置
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        //如果系统没有消费者的偏移量, 系统会读取该分区最早的偏移量
        //props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        //如果有了偏移量的话,  latest 和 earliest 是一样的

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        //2.订阅相关的topic
        consumer.subscribe(Arrays.asList("topic05"));

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
