package com.zbl.threadObServer;


/*
* 线程状态的JAVABean
* */
public class RunnableEvent{


         private final  RunnableState state;

         private final Thread thread;

         private final Throwable throwable;

        public RunnableEvent(RunnableState state, Thread thread, Throwable throwable) {
            this.state = state;
            this.thread = thread;
            this.throwable = throwable;
        }

        public RunnableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getThrowable() {
            return throwable;
        }
    }
