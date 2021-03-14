package com.zbl.wwj.concurrent.step2.p51;

import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/13
 * @Description:
 * @Version: $
 */
public class ThreadLifeCycleObserver implements LiftCycleListener {

    private final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        ids.stream().forEach(id -> new Thread(new ObservableRunnable(this) {
            @Override
            public void run() {
                try {
                    notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                    System.out.println("query for the id " + id);
                    Thread.sleep(1000L);
                    notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                } catch (Exception e) {
                    notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                }
            }
        }, id).start());
    }

    @Override
    public void onEvent(ObservableRunnable.RunnableEvent event) {
        synchronized (LOCK) {
            System.out.println("The runnable [" + event.getThread().getName() + "] data change and state is " + "[" + event.getState() + "]");
            if (event.getE() != null) {
                System.out.println("The runnable [" + event.getThread().getName() + "] process failed.");
                event.getE().printStackTrace();
            }
        }
    }
}
