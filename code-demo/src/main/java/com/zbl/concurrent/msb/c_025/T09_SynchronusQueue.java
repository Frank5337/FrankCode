package com.zbl.concurrent.msb.c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class T09_SynchronusQueue { //容量为0
	public static void main(String[] args) throws InterruptedException {
		//同步队列, 特殊的transferQueue
		BlockingQueue<String> strs = new SynchronousQueue<>();
		
		new Thread(()->{
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		strs.put("aaa"); //阻塞等待消费者消费  其实里面就是transfer 特殊的transferQueue
		//strs.add("aaa");  容量为0 add不进去  只能调用put
		System.out.println(strs.size());
	}
}
