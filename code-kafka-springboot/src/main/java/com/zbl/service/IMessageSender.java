package com.zbl.service;

/**
 * @Author: zbl
 * @Date: Created in 2020/11/30
 * @Description:
 * @Version: $
 */
public interface IMessageSender {

    public void sendMessage(String topic, String key, String message);
}
