package com.zbl.wwj.concurrent.step2.p77;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 15:34
 * @Description:
 * @Version: $
 */
public final class ActiveObjectFactory {

    private ActiveObjectFactory() {
    }

    public static ActiveObject createActiveObject() {
        Servant servant = new Servant();
        ActivationQueue queue = new ActivationQueue();
        SchedulerThead schedulerThead = new SchedulerThead(queue);
        ActiveObjectProxy proxy = new ActiveObjectProxy(schedulerThead, servant);
        schedulerThead.start();
        return proxy;
    }
}
