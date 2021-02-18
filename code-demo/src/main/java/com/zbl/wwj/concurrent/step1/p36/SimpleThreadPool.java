package com.zbl.wwj.concurrent.step1.p36;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/11
 * @Description:
 * @Version: $
 */
public class SimpleThreadPool {

    //线程池需要哪些东西
    //1.任务队列
    //2.拒绝策略(任务不能无限丢进来, [抛出异常, 丢弃, 阻塞, 临时队列])
    //3.init
    //4.active
    //5.max 最大线程个数是多少,  (不能无限增加, 无限增加会导致虚拟机栈溢出
    //6.min  min => active => max  min到max扩充

    private final int size;

    private static final int DEFAULT_SIZE = 5;

    private static volatile AtomicInteger seq = new AtomicInteger(0);

    private static final String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    private static final ThreadGroup GROUP = new ThreadGroup("Pool-Group");

    private static final LinkedList<Runnable> TASK_QUEUE = new LinkedList();

    private static final List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    public SimpleThreadPool() {
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size) {
        this.size = size;
        init();
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            createWorkTask();
        }
    }

    public void submit(Runnable runnable) {
        synchronized (TASK_QUEUE) {
            TASK_QUEUE.addLast(runnable);
            //通知在接活的兄弟们
            TASK_QUEUE.notifyAll();
        }
    }

    private void createWorkTask() {
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + seq.addAndGet(1));
        task.start();
        THREAD_QUEUE.add(task);
    }

    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD
    }

    private static class WorkerTask extends Thread {

        private volatile TaskState taskState = TaskState.FREE;

        private int currentExecCount = 0;

        public WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        public TaskState getTaskState() {
            return this.taskState;
        }

        public void close() {
            this.taskState = TaskState.DEAD;
        }

        @Override
        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            break OUTER;
                        }

                    }
                    //从头获取  从尾巴进来  保证顺序
                    runnable = TASK_QUEUE.removeFirst();
                }

                if (runnable != null) {
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    System.out.println(Thread.currentThread().getName() +"/" + ++currentExecCount);
                    taskState = TaskState.FREE;
                }
            }
        }
    }
}
