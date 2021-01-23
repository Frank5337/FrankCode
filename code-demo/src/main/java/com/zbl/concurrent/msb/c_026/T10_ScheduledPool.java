package com.zbl.concurrent.msb.c_026;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T10_ScheduledPool {
	public static void main(String[] args) {
		//定时执行线程池, 线程可复用
		ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
		//以固定的频率执行某个线程
		//4个参数
		// 1.runnable, 2.第一个任务执行的延迟时间, 3 每个多长时间执行一个任务, 4.时间单位
		service.scheduleAtFixedRate(()->{
			try {
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		}, 0, 500, TimeUnit.MILLISECONDS);
		
	}
}
