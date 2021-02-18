package com.zbl.msb.concurrent.c_024;

import java.util.ArrayList;
import java.util.List;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 *
 *
 * @author 马士兵
 */
public class TicketSeller1 {
	static List<String> tickets = new ArrayList<>();

	static List<String> sell = new ArrayList<>();

	static {
		for(int i=0; i<100000; i++) tickets.add("票编号" + i);
	}
	
	
	
	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
			new Thread(()->{
				while(tickets.size() > 0) {
					String remove = tickets.remove(0);
					//addRem(sell, remove);  remove也不是原子性的
					System.out.println("销售了--" + remove);
				}
			}).start();
		}
	}

//  测试重复和超卖
/*	public static void addRem(List<String> a, String r) {
		if (a.size() > 0) {
			a.forEach( s -> {
				if (s != null && s.equals(r)) {
					System.out.println("有重复的: " + r);
				}
			});
		}
		a.add(r);
	}*/
}
