package com.zbl.threadObServer;

/**
 *   代表subject 角色。所有的状态都将通过notifyAll，调用obServerListener的onEvent方法去通知各个Observer，实现Observer对subject的监听。
 */
public class ObSserverRunnable implements Runnable {

    private final ObServerListener obServerListener;

    public ObSserverRunnable(final ObServerListener obServerListener) {
        this.obServerListener = obServerListener;
    }




    public void notifyAlla(RunnableEvent event){
        obServerListener.onEvnt(event);
    }


    @Override
    public void run() {

        try {
            notifyAlla(new RunnableEvent(RunnableState.RUNNING,Thread.currentThread(),null));
            System.out.println("query for thread: "+Thread.currentThread().getName());
            Thread.sleep(5000);
            notifyAlla(new RunnableEvent(RunnableState.DONE,Thread.currentThread(),null));
        }catch (Exception e){
            notifyAlla(new RunnableEvent(RunnableState.ERROR,Thread.currentThread(),null));

        }

    }
}
