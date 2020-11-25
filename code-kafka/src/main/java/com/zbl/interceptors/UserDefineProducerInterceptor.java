package com.zbl.interceptors;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @Author: zbl
 * @Date: Create in 2020/11/25
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class UserDefineProducerInterceptor implements ProducerInterceptor {
    @Override
    public ProducerRecord onSend(ProducerRecord record) {
        System.out.println("headers:" + record.headers());
        //headers:RecordHeaders(headers = [], isReadOnly = false)
        return new ProducerRecord(record.topic(), record.key(), record.value() + " -- zbl5337");
    }

    /**
     * 每次发送完  不管成功失败  都会调用此方法,  (通知)
     * @param metadata 调用成功元数据
     * @param exception 调用失败异常信息
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        System.out.println("metadata:" + metadata + ", exception:" + exception);
        //metadata:topic01-1@63, exception:null
    }

    /**
     * 生命周期方法
     * 可以做一些资源的释放
     */
    @Override
    public void close() {
        System.out.println("UserDefineProducerInterceptor::close");
    }

    /**
     * 生命周期方法
     * 可以获取生产者配置信息
     */
    @Override
    public void configure(Map<String, ?> configs) {
        System.out.println("UserDefineProducerInterceptor::configure");
    }
}
