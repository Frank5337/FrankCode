package com.zbl.transactional;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.*;

/**
 * @Author: zbl
 * @Date: Create in 2020/11/23
 * @Email: zbl5337@gmail.com
 * @Description: 生产者事务
 *               一个中间流转的, 接收topic01的数据 发送给topic02
 */
public class KafkaProducerTransactionalProducerAndConsumer {
    public static void main(String[] args) {

        KafkaProducer<String, String> producer = buildKafkaProducer();
        KafkaConsumer<String, String> consumer = buildKafkaConsumer("t3");

        //初始化事务
        producer.initTransactions();

        consumer.subscribe(Arrays.asList("topic01"));
        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            if (!consumerRecords.isEmpty()) {
                Iterator<ConsumerRecord<String, String>> recordIterator = consumerRecords.iterator();

                Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
                //开启事务控制
                producer.beginTransaction();
                try {
                    //迭代数据, 进行业务处理
                    while (recordIterator.hasNext()) {
                        ConsumerRecord<String, String> record = recordIterator.next();
                        //存储元数据
                        offsets.put(new TopicPartition(record.topic(), record.partition()),
                                new OffsetAndMetadata(record.offset()));
                        //
                        ProducerRecord<String, String> producerRecord =
                                new ProducerRecord<String, String>("topic02", 0 ,record.key(),
                                        record.value() + " zbl online topic01->topic02   ");
                        System.out.println("收到了 -> 发送:" + record);
                        producer.send(producerRecord);
                    }
                    //提交事务

                    //提交消费者的偏移量
                    producer.sendOffsetsToTransaction(offsets, "t3");
                    producer.commitTransaction();
                } catch (Exception e) {
                    System.err.println("错误了~" + e.getMessage());
                }
            }
        }


    }

    public static KafkaProducer<String, String> buildKafkaProducer() {
        //1.创建KafkaProducer
        //泛型, 发送的record 的key类型和值类型
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "144-kafka-a:9092,145-kafka-b:9092,146-kafka-c:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //必须配置事务ID, 必须是唯一
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transaction=id" + UUID.randomUUID().toString());

        //配置kafka批处理大小 默认16384
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 1024);

        //默认是0 不等待 单位ms   等待5ms, 如果batch中数据不足1024, 会等待5ms
        props.put(ProducerConfig.LINGER_MS_CONFIG, 5);

        //配置kafka的重试机制 和 幂等   ack=all leader等待全套副本确认 才算确认
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        //20秒没收到ACK就超时
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 20000);

        return new KafkaProducer<>(props);
    }

    public static KafkaConsumer<String, String> buildKafkaConsumer(String groupId) {
        //1.创建KafkaConsumer
        //泛型, 发送的record 的key类型和值类型
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "144-kafka-a:9092,145-kafka-b:9092,146-kafka-c:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        //设置消费者的消费事务的隔离级别 read_committed
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        //必须关闭消费者端的 offset自动提交(必须等待生产者把业务逻辑处理完才做自动提交)
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        return new KafkaConsumer<>(props);
    }

}
