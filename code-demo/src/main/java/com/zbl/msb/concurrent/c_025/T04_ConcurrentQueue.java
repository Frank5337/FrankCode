package com.zbl.msb.concurrent.c_025;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T04_ConcurrentQueue {
	public static void main(String[] args) {
		//无界队列
		Queue<String> strs = new ConcurrentLinkedQueue<>();
		
		for(int i=0; i<10; i++) {
			strs.offer("a" + i);  //add
		}
		
		System.out.println(strs);
		
		System.out.println(strs.size());
		//弹出(删除) 并返回顶部元素
		System.out.println(strs.poll());
		System.out.println(strs.size());
		//返回顶部元素
		System.out.println(strs.peek());
		System.out.println(strs.size());
		
		//双端队列Deque
		//ConcurrentLinkedDeque 双端队列
	}
}
