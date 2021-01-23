package com.zbl.concurrent.msb.c_003;


/**
 * synchronized关键字
 * 对某个对象加锁
 * @author mashibing
 */

public class T {

	private int count = 10;
	
	public synchronized void m() { //等同于在方法的代码执行时要synchronized(this)
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}

}
