package com.zbl.concurrent.msb.c_001;


/**
 * synchronized关键字
 * 对某个对象加锁
 * @author mashibing
 */
public class T {
	
	private int count = 10;
	private Object o = new Object();
	
	public void m() {
		synchronized(o) { //任何线程要执行下面的代码，必须先拿到o的锁( 0 -> 堆内存里面new出来的对象) 一个线程执行完会释放
			// 互斥锁, 只要有一个人拿到这个锁, 其他人就拿不到了
			count--;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
		}
	}
	
}

