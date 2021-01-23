package com.zbl.concurrent.msb.c_024;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 *
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？
 *
 * 就算操作A和B都是同步的，但A和B组成的复合操作也未必是同步的，仍然需要自己进行同步
 * 就像这个程序，判断size和进行remove必须是一整个的原子操作
 *
 * 使用ConcurrentQueue提高并发性
 *
 * @author 马士兵
 */
public class TicketSeller4 {
	//所谓的队列, 其实就是个容器  并发的链表的队列
	static Queue<String> tickets = new ConcurrentLinkedQueue<>();
	
	
	static {
		for(int i=0; i<1000; i++) tickets.add("票 编号：" + i);
	}
	
	public static void main(String[] args) {
		
		for(int i=0; i<10; i++) {
			new Thread(()->{
				while(true) {
					//poll 往外拿一个数据   poll是同步的 如果没拿到 会返回null
					//检索并返回此队列的头  poll底层实现是CAS
					String s = tickets.poll();
					//虽然不是原子的, 但是 做了判断之后没有修改操作,  所有不会有问题
					//不加锁  效率会很高
					if(s == null) break;
					else System.out.println("销售了--" + s);
				}
			}).start();
		}
	}
}
