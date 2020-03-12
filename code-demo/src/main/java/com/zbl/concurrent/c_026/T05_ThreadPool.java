package com.zbl.concurrent.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的概念
 */
public class T05_ThreadPool {
	public static void main(String[] args) throws InterruptedException {
		//execute 执行的是Runnable方法, submit可以执行没返回值的, 也可以执行有返回值的
		//自动调用Runnable的run方法
		ExecutorService service = Executors.newFixedThreadPool(5); //execute submit
		for (int i = 0; i < 6; i++) {
			service.execute(() -> {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		System.out.println(service);
		//java.util.concurrent.ThreadPoolExecutor@723279cf[Running, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
		//queued tasks = 1 当前排队的线程,  一个有5个线程, 6个任务 所以会有一个等待

		//正常关闭线程池, 等所有线程执行完关闭
		service.shutdown();
		//是否被终止
		System.out.println(service.isTerminated());
		//是否关闭 (关了并不代表执行完了)
		System.out.println(service.isShutdown());

		//java.util.concurrent.ThreadPoolExecutor@723279cf[Shutting down, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
		//Shutting down , 正在关闭
		System.out.println(service);
		
		TimeUnit.SECONDS.sleep(5);
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		//java.util.concurrent.ThreadPoolExecutor@723279cf[Terminated, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 6]
		//Terminated 已终止  completed tasks = 6  排队的队列, 结束的队列 维护两个队列
		System.out.println(service);
	}
}
