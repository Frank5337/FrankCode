package com.zbl.concurrent.msb.c_004;


/**
 * synchronized关键字
 * 对某个对象加锁
 * @author mashibing
 */
public class T {

	private static int count = 10;
	
	public synchronized static void m() { //这里等同于synchronized(yxxy.c_004.T.class)
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void mm() {
		synchronized(T.class) { //考虑一下这里写synchronized(this)是否可以？ 不可以 static不需要实例就可以访问的
			count --;
		}
	}

}
