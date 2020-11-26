package com.zbl.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zbl
 * @Date: Create in 2020/11/24
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class UserDefinePartitioner implements Partitioner {

    private AtomicInteger counter = new AtomicInteger(0);

    /**
     * 返回分区号
     * @param topic
     * @param key
     * @param keyBytes
     * @param value
     * @param valueBytes
     * @param cluster
     * @return
     */
    @Override
    public int partition(String topic,
                         Object key, byte[] keyBytes,
                         Object value, byte[] valueBytes,
                         Cluster cluster) {
        //获取所有分区
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();
        if (keyBytes == null) {
            int andIncrement = counter.getAndIncrement();
            //Integer.MAX_VALUE 最高位是0 保证符号位变为正数
            return (andIncrement & Integer.MAX_VALUE) % numPartitions;
        } else {
            // hash the keyBytes to choose a partition
            return Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions;
        }

    }

    /**
     * 生命周期方法
     */
    @Override
    public void close() {
        System.out.println("UserDefinePartitioner::close");
    }

    /**
     * 生命周期方法
     */
    @Override
    public void configure(Map<String, ?> configs) {
        System.out.println("UserDefinePartitioner::configure");
    }
}
