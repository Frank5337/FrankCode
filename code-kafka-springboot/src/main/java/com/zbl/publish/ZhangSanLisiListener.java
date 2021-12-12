package com.zbl.publish;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: zbl
 * @Date: Created in 2021/8/26 14:57
 * @Description:
 * @Version: $
 */
@Component
public class ZhangSanLisiListener implements ApplicationListener<ContentEvent> {
    @Override
    public void onApplicationEvent(ContentEvent event) {
        System.out.println("张三收到了消息: " + event.getSource());
    }
}
