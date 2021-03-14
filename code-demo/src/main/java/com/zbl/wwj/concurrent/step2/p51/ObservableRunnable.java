package com.zbl.wwj.concurrent.step2.p51;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/13
 * @Description:
 * @Version: $
 */
public abstract class ObservableRunnable implements Runnable {

    final LiftCycleListener listener;

    public ObservableRunnable(final LiftCycleListener listener) {
        this.listener = listener;
    }

    protected void notifyChange(final RunnableEvent event) {
        listener.onEvent(event);
    }

    public enum RunnableState {
        RUNNING, ERROR, DONE;
    }

    public static class RunnableEvent {
        private final RunnableState state;
        private final Thread thread;
        private final Throwable e;

        public RunnableEvent(RunnableState state, Thread thread, Throwable e) {
            this.state = state;
            this.thread = thread;
            this.e = e;
        }

        public RunnableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getE() {
            return e;
        }
    }


}
