package com.zbl.concurrent.c_024;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 分析下面的程序可能会产生哪些问题？
 *
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？
 *
 * @author 马士兵
 */
public class TicketSeller2 {
	//vector 同步容器
	static Vector<String> tickets = new Vector<>();
	
	
	static {
		for(int i=0; i<1000; i++) tickets.add("票 编号:" + i);
	}
	
	public static void main(String[] args) {
		
		for(int i=0; i<10; i++) {
			new Thread(()->{
				//在vector里面 remove方法虽然是原则的,
				//但是  判断和操作分离了,  导致不是一个原子性的操作,  两个原子性操作中间 可能出现问题
				//所以依然还是会出现 超卖, 重复卖的问题
				while(tickets.size() > 0) {
					
					try {
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					
					System.out.println("销售了--" + tickets.remove(0));
				}
			}).start();
		}
	}
}
