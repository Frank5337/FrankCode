package com.zbl.quickstart;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.record.TimestampType;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * @Author: zbl
 * @Date: Create in 2020/11/23
 * @Email: zbl5337@gmail.com
 * @Description: 不设置组, 手动指定消费分区, 没有了组管理的概念,  相互独立
 */
public class KafkaConsumerQuickStart_02 {
    public static void main(String[] args) {
        //1.创建KafkaConsumer
        //泛型, 发送的record 的key类型和值类型
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "144-kafka-a:9092,145-kafka-b:9092,146-kafka-c:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //props.put(ConsumerConfig.GROUP_ID_CONFIG, "g1");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        //2.订阅相关的topic, 手动指定消费分区, 失去组管理特性
        List<TopicPartition> partitions = Arrays.asList(new TopicPartition("topic01", 1));
        consumer.assign(partitions);
        //设置消费分区的指定位置
        //开始位置
        //consumer.seekToBeginning(partitions);

        //偏移量10
        consumer.seek(new TopicPartition("topic01", 1), 10);

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
