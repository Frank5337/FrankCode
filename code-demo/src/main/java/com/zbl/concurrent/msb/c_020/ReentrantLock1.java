package com.zbl.concurrent.msb.c_020;

import java.util.concurrent.TimeUnit;

/**
 * reentrantlock 用于替代synchronized
 * 本例中由于m1锁定this,只有m1执行完毕的时候,m2才能执行
 * 这里是复习synchronized最原始的语义
 * @author mashibing
 *
 * java高并发主要3块知识点
 * 同步器 synchronizer
 * 同步容器 CurrentMap CurrentList CopyOnWrite ....
 * ThreadPool , executor future ...
 */
public class ReentrantLock1 {
	synchronized void m1() {
		for(int i=0; i<10; i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}
		
	}
	
	synchronized void m2() {
		System.out.println("m2 ...");
	}
	
	public static void main(String[] args) {
		ReentrantLock1 rl = new ReentrantLock1();
		new Thread(rl::m1).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(rl::m2).start();
	}
}
