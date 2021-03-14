package com.zbl.wwj.concurrent.step2.p51;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/13
 * @Description:
 * @Version: $
 */
public interface LiftCycleListener {

    void onEvent(ObservableRunnable.RunnableEvent event);
}
