package com.zbl.concurrent.msb.c_002;


/**
 * synchronized关键字
 * 对某个对象加锁
 * @author mashibing
 */

public class T extends Thread {
	
	private int count = 10;
	//当前T1  T2  T3
	public void m() {
		synchronized(this) {  //任何线程要执行下面的代码，必须先拿到this的锁 (相当于new一个T 指向自身)
			count--;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
			for (int i = 0; i < 10000 ; i++) {
				System.out.println(Thread.currentThread().getName());
			}
		}
	}

	//所有T
	public void m1() {
		synchronized(T.class) {
			count--;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
			for (int i = 0; i < 10000 ; i++) {
				System.out.println(2);
			}
		}
	}

	/**
	 * 交替执行的  this 指的是当前对象 t
	 * @param args
	 */
//	public static void main(String[] args) {
//		T t = new T();
//		new Thread(t::m).start();
//
//		T t1 = new T();
//		new Thread(t::m).start();
//	}

	/**
	 * 不交替执行的  this 指的是当前对象 t
	 * @param
	 */
	public static void main(String[] args) {
		T t = new T();
		new Thread(t::m).start();
		new Thread(t::m).start();
	}

	
}

