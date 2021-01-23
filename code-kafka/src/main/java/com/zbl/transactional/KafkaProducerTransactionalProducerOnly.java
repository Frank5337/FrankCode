package com.zbl.transactional;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;

/**
 * @Author: zbl
 * @Date: Create in 2020/11/23
 * @Email: zbl5337@gmail.com
 * @Description: 生产者事务
 */
public class KafkaProducerTransactionalProducerOnly {
    public static void main(String[] args) {

        KafkaProducer<String, String> producer = buildKafkaProducer();

        //初始化事务
        producer.initTransactions();

        try {
            //开启事务
            producer.beginTransaction();
            for (int i = 0; i < 5; i++) {
//                if (i == 3) {
//                    i = 1 / 0;
//                }
                ProducerRecord<String, String> record = new ProducerRecord<>("topic01", 1,
                        "key:" + i, "value transaction:" + i);
                // 没有key 就是轮询的
                //            ProducerRecord<String, String> record = new ProducerRecord<>("topic01",  "value:" + i);
                //发送给消息服务器
                producer.send(record);
                producer.flush();
            }
            //事务提交
            producer.commitTransaction();
        } catch (Exception e) {
            System.err.println("出现错误了, 回滚了, " + e.getMessage());
            //终止事务
            producer.abortTransaction();
        } finally {
            //关闭生产者
            producer.close();
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

}
