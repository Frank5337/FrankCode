package com.zbl.publish;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: zbl
 * @Date: Created in 2021/8/26 14:57
 * @Description:
 * @Version: $
 */
public class ContentEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ContentEvent(Object source) {
        super(source);
    }

    public void exec() {
        System.out.println("event exec 嘻嘻");
    }
}
