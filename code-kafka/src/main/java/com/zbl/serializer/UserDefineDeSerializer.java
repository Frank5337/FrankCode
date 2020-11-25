package com.zbl.serializer;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @Author: zbl
 * @Date: Created in 2020/11/25
 * @Description:
 * @Version: $
 */
public class UserDefineDeSerializer implements Deserializer<Object> {
    /**
     * 生命周期方法
     * @param configs
     * @param isKey
     */
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        System.out.println("UserDefineDeSerializer::configure");
    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        return SerializationUtils.deserialize(data);
    }


    /**
     * 生命周期方法
     */
    @Override
    public void close() {
        System.out.println("UserDefineDeSerializer::close");
    }
}
