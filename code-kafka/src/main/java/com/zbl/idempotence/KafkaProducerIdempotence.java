package com.zbl.idempotence;

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
public class KafkaProducerIdempotence {
    public static void main(String[] args) {
        //1.创建KafkaProducer
        //泛型, 发送的record 的key类型和值类型
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "144-kafka-a:9092,145-kafka-b:9092,146-kafka-c:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //设置kafka Acks以及retries
        //all 至少有1个副本同步了数据
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        //重试3次 不包含第一次发送  相当于发4次
        props.put(ProducerConfig.RETRIES_CONFIG, 3);

        //为了达到效果, 让他请求超时 将检测超时时间设置为1ms
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 1);

        //开启幂等性
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        //让记录严格有序, 默认是5  有5个消息未被确认的话 就阻塞客户端
        //如果有1个发送不成功, 就阻塞
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);


        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        //for (int i = 0; i < 5; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>("topic01",
                    "key:" + "idempotence", "value:" + "idmtc");
            // 没有key 就是轮询的
            //            ProducerRecord<String, String> record = new ProducerRecord<>("topic01",  "value:" + i);
            //发送给消息服务器
            producer.send(record);
            //防止在本地做缓冲
            producer.flush();
        //}

        //关闭生产者
        producer.close();
    }
}
