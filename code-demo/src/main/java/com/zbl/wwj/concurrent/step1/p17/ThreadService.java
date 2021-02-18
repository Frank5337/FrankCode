package com.zbl.wwj.concurrent.step1.p17;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/2 12:21
 * @Description:
 * @Version: $
 */
public class ThreadService {

    //执行线程
    private Thread executeThread;

    private boolean finished = false;

    public void execute(Runnable task) {
        executeThread = new Thread() {
            @Override
            public void run() {
                Thread runner = new Thread(task);
                runner.setDaemon(true);
                runner.start();
                try {
                    runner.join();
                    finished = true;
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
        };
        executeThread.start();
    }

    public void shutdown(long mills) {
        long currentTime = System.currentTimeMillis();
        while (!finished) {
            //超时
            long timeout = 0L;
            if ((timeout = System.currentTimeMillis() - currentTime) >= mills) {
                System.out.println("任务超时, 需要结束: " + timeout);
                executeThread.interrupt();
                break;
            }

            try {
                //让执行线程去休眠, 当前线程继续执行
                executeThread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断");
                break;
            }

        }
    }

}
