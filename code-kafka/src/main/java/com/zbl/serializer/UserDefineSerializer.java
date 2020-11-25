package com.zbl.serializer;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.serialization.Serializer;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: zbl
 * @Date: Created in 2020/11/25
 * @Description:
 * @Version: $
 */
public class UserDefineSerializer implements Serializer<Object> {
    /**
     * 生命周期方法
     *
     * @param configs
     * @param isKey
     */
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        System.out.println("UserDefineSerializer::configure");
    }

    @Override
    public byte[] serialize(String topic, Object data) {
        return SerializationUtils.serialize((Serializable) data);
    }

    /**
     * 生命周期方法
     */
    @Override
    public void close() {
        System.out.println("UserDefineSerializer::close");
    }
}
