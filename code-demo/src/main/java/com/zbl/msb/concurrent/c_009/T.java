package com.zbl.msb.concurrent.c_009;


/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁.
 * 也就是说synchronized获得的锁是可重入的  获得锁之后还可以再获得一遍
 * @author mashibing
 *
 * 重入锁
 */
import java.util.concurrent.TimeUnit;

public class T {
	synchronized void m1() {
		synchronized (T.class) {
			System.out.println("m1 start");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			m2();
		}

	}
	
	synchronized void m2() {
		synchronized (T.class) {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("m2");
		}

	}

	public static void main(String[] args) {
		new T().m1();
	}
}
