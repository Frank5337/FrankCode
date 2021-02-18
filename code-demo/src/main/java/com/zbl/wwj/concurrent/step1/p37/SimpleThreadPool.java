package com.zbl.wwj.concurrent.step1.p37;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/11
 * @Description:
 * @Version: $
 */
public class SimpleThreadPool extends Thread {

    //线程池需要哪些东西
    //1.任务队列
    //2.拒绝策略(任务不能无限丢进来, [抛出异常, 丢弃, 阻塞, 临时队列])
    //3.init
    //4.active
    //5.max 最大线程个数是多少,  (不能无限增加, 无限增加会导致虚拟机栈溢出
    //6.min  min => active => max  min到max扩充

    private int size;

    //等待队列最大值
    private final int queueSize;

    private static final int DEFAULT_SIZE = 5;

    private static final int DEFAULT_TASK_QUEUE_SIZE = 2000;

    private static volatile AtomicInteger seq = new AtomicInteger(0);

    private static final String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    private static final ThreadGroup GROUP = new ThreadGroup("Pool-Group");

    private static final LinkedList<Runnable> TASK_QUEUE = new LinkedList();

    private static final List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    private final DiscardPolicy discardPolicy;

    private volatile boolean destroy = false;

    //最小线程
    private int min;

    //最大
    private int max;

    //活跃
    private int active;

    public static final DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("Discard This task");
    };

    public SimpleThreadPool() {
        this(4, 8, 12, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy) {
        //任务队列大小
        this.min = min;
        this.active = active;
        this.max = max;
        //等待队列大小
        this.queueSize = queueSize;
        //拒绝策略
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init() {
        for (int i = 0; i < this.min; i++) {
            createWorkTask();
        }
        this.size = min;
        this.start();
    }

    public void submit(Runnable runnable) {
        if (destroy) {
            throw new IllegalStateException("The thread pool already destroy and not allow submit task.");
        }
        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() + 1 > queueSize) {
                discardPolicy.discard();
            }
            TASK_QUEUE.addLast(runnable);
            //通知在接活的兄弟们
            TASK_QUEUE.notifyAll();
        }
    }

    @Override
    public void run() {
        while (!destroy) {
            System.out.printf("Pool#Min: %d, Active: %d, Max: %d, Current: %d, QueueSize: %d\n",
                    this.min, this.active, this.max, this.size, TASK_QUEUE.size());
            try {
                Thread.sleep(5_000);
                //开启
                if (TASK_QUEUE.size() > active && size < active) {
                    //size = size + active - size;
                    for (int i = 0; i < active - size; i++) {
                        createWorkTask();
                    }
                    System.out.println("The Pool incremented. to Active");
                    size = active;
                } else if (TASK_QUEUE.size() > max && size < max) {
                    for (int i = 0; i < max - size; i++) {
                        createWorkTask();
                    }
                    System.out.println("The Pool incremented. to Max");
                    size = max;
                }

                //关闭
                if (TASK_QUEUE.isEmpty() && size > active) {
                    synchronized (THREAD_QUEUE) {
                        int release = size - active;
                        for (Iterator<WorkerTask> it = THREAD_QUEUE.iterator(); it.hasNext(); ) {
                            if (release <= 0) {
                                break;
                            }
                            WorkerTask task = it.next();
                            System.out.println("Release::" + task);
                            task.close();
                            task.interrupt();
                            it.remove();
                            release--;
                        }
                        size = active;

                    }
                }
                System.out.println("THREAD_QUEUE=" + THREAD_QUEUE.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createWorkTask() {
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + seq.addAndGet(1));
        task.start();
        THREAD_QUEUE.add(task);
    }

    //等所有任务结束之后 再关闭线程
    public void shutdown() throws InterruptedException {
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(50);
        }

        synchronized (THREAD_QUEUE) {
            int initVal = THREAD_QUEUE.size();
            while (initVal > 0) {
                for (WorkerTask task : THREAD_QUEUE) {
                    if (task.getTaskState() == TaskState.BLOCKED) {
                        task.interrupt();
                        task.close();
                        initVal--;
                    } else {
                        Thread.sleep(10);
                    }
                }
            }
            this.destroy = true;
            System.out.println("The thread pool disposed.");
        }

    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getActive() {
        return active;
    }

    public int getSize() {
        return size;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public boolean isDestroy() {
        return this.destroy;
    }

    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD
    }

    public static class DiscardException extends RuntimeException {

        public DiscardException(String message) {
            super(message);
        }
    }

    public interface DiscardPolicy {

        public void discard() throws DiscardException;

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
                    System.out.println(Thread.currentThread().getName() + "/" + ++currentExecCount);
                    taskState = TaskState.FREE;
                }
            }
        }
    }
}
