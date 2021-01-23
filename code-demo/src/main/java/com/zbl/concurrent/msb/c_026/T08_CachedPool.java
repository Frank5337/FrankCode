package com.zbl.concurrent.msb.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T08_CachedPool {
	public static void main(String[] args) throws InterruptedException {
		//刚开始, 一个线程都没有, 来一个启动一个
		//如果有空闲的 就用空闲的, 起到系统能支撑的线程为止
		//默认情况 空闲线程超过60秒 会自毁 可以指定
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println(service);
		
		for (int i = 0; i < 2; i++) {
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
		
		TimeUnit.SECONDS.sleep(80);
		
		System.out.println(service);
		
		
	}
}
