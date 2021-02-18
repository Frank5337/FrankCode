package com.zbl.wwj.concurrent.threadObServer;

/*
 * 实现ObServerListener之后，subject线程的改变将通过实现的方法来，onEvnt来实现监听。
 * */

public class ThreadObServer implements ObServerListener {
    final private Object object = new Object();


    @Override
    public void onEvnt(RunnableEvent event) {
        synchronized (object) {
            System.out.println("the runnable [" + event.getThread().getName() + "] data changed and state is [" + event.getState() + "]");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (event.getThrowable() != null) {
                System.out.println("the runnable [" + event.getThread().getName() + "] process failed");
                event.getThrowable().printStackTrace();
            }
        }
    }
}
