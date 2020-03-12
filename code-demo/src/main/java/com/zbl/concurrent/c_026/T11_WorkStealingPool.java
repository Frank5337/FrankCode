package com.zbl.concurrent.c_026;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T11_WorkStealingPool {
	public static void main(String[] args) throws IOException {
		//工作窃取
		//偷任务的线程池
		//每个线程都维护自己的队列, 当A执行完, 会去找其他(b, c) 的里面去找线程去执行
		//会主动的去找活干
		ExecutorService service = Executors.newWorkStealingPool();
		System.out.println(Runtime.getRuntime().availableProcessors());

		service.execute(new R(1000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000)); //daemon
		service.execute(new R(2000));

		//由于产生的是精灵线程（守护线程、后台线程），主线程不阻塞的话，看不到输出
		System.in.read();//让main方法阻塞
		//精灵线程, 虚拟机不退出他不退出

//		4
//		1000 ForkJoinPool-1-worker-2
//		2000 ForkJoinPool-1-worker-1
//		2000 ForkJoinPool-1-worker-0
//		2000 ForkJoinPool-1-worker-3
//		2000 ForkJoinPool-1-worker-2
	}

	static class R implements Runnable {

		int time;

		R(int t) {
			this.time = t;
		}

		@Override
		public void run() {
			
			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(time  + " " + Thread.currentThread().getName());
			
		}

	}
}
