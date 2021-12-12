package com.zbl.publish;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: zbl
 * @Date: Created in 2021/8/26 14:57
 * @Description:
 * @Version: $
 */
@Component
public class LisiListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContentEvent) {
            System.out.println("copy copy ! ! :" + event.getSource());
            ((ContentEvent)event).exec();
        }
    }
}
