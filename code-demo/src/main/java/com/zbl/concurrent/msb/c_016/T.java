package com.zbl.concurrent.msb.c_016;

import java.util.concurrent.TimeUnit;

/**
 * synchronized优化
 * 同步代码块中的语句越少越好
 * 比较m1和m2
 * @author mashibing
 */
public class T {
	
	int count = 0;

	synchronized void m1() {
		//do sth need not sync
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//业务逻辑中只有下面这句需要sync，这时不应该给整个方法上锁
		count ++;
		
		//do sth need not sync
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void m2() {
		//do sth need not sync
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//业务逻辑中只有下面这句需要sync，这时不应该给整个方法上锁
		//采用细粒度的锁，可以使线程争用时间变短，从而提高效率
		synchronized(this) {
			count ++;
		}
		//do sth need not sync
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		long s1 = System.currentTimeMillis();
		//new Thread(() -> new T().m1()).start();
		new Thread(() -> new T().m2()).start();
		long s2 = System.currentTimeMillis();
		System.out.println(s2 - s1);
	}

	

}
