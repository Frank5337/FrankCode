package com.zbl.concurrent.msb.c_005;


/**
 * 分析一下这个程序的输出
 * @author mashibing
 */
public class T implements Runnable {

	private int count = 10;
	
	public synchronized void run() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}

	/**
	 * 这种打断叫线程重入  加synchronized解决  一个synchronized 相当于一个原子操作   一个synchronized的代码块是原子操作 不可分
	 * @param args
	 */
	public static void main(String[] args) {
		T t = new T();
		for(int i=0; i<5; i++) {
			new Thread(t, "THREAD" + i).start();
		}
	}
	
}
