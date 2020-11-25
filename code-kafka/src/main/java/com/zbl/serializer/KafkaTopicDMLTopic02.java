package com.zbl.serializer;

import org.apache.kafka.clients.admin.*;

import java.util.Arrays;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @Author: zbl
 * @Date: Create in 2020/11/23
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class KafkaTopicDMLTopic02 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1.创建KafkaAdminClient
        Properties prpos = new Properties();
        prpos.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                "144-kafka-a:9092,145-kafka-b:9092,146-kafka-c:9092");
        KafkaAdminClient adminClient = (KafkaAdminClient) KafkaAdminClient.create(prpos);

        //创建Topic信息,  异步创建的
        CreateTopicsResult createTopicsResult = adminClient.createTopics(Arrays.asList(new NewTopic("topic02", 3, (short) 3)));

        //异步改成同步
        createTopicsResult.all().get();

        //查看Topic列表
        ListTopicsResult topicsResult = adminClient.listTopics();
        Set<String> names = topicsResult.names().get();
        names.forEach(System.out::println);

        //topic的删除
        //DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Arrays.asList("topic02", "topic03"));
        //同步删除
        //deleteTopicsResult.all().get();

        //查看Topic详细信息
//        DescribeTopicsResult topic01 = adminClient.describeTopics(Arrays.asList("topic01"));
//        Map<String, TopicDescription> descriptionMap = topic01.all().get();
//        descriptionMap.forEach((key, value) -> System.out.println(key + "\t" + value));
        //topic01	(name=topic01,
        //           internal=false,
        //           partitions=
        //
        //           (partition=0, leader=145-kafka-b:9092 (id: 1 rack: null),
        //           replicas=146-kafka-c:9092 (id: 2 rack: null),
        //           145-kafka-b:9092 (id: 1 rack: null),
        //           isr=145-kafka-b:9092 (id: 1 rack: null), 146-kafka-c:9092 (id: 2 rack: null)),

        //           (partition=1, leader=145-kafka-b:9092 (id: 1 rack: null),
        //           replicas=145-kafka-b:9092 (id: 1 rack: null),
        //           144-kafka-A:9092 (id: 0 rack: null),
        //           isr=145-kafka-b:9092 (id: 1 rack: null), 144-kafka-A:9092 (id: 0 rack: null)),

        //           (partition=2, leader=146-kafka-c:9092
        //           (id: 2 rack: null), replicas=144-kafka-A:9092 (id: 0 rack: null),
        //           146-kafka-c:9092 (id: 2 rack: null),
        //           isr=146-kafka-c:9092 (id: 2 rack: null), 144-kafka-A:9092 (id: 0 rack: null)))


        //关闭AdminClient
        adminClient.close();
    }
}
