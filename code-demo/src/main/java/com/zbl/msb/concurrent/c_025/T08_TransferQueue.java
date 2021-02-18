package com.zbl.msb.concurrent.c_025;

import java.util.concurrent.LinkedTransferQueue;

public class T08_TransferQueue {
	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

		//先开启消费者 再生产
		/*new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/

		//先启动消费者  再启动生产者
		//生产者生产的时候, 如果有消费者在等待, 则把生产好的直接丢给消费者, 不需要再放到队列中, 消费者再从队列中取
		//效率会更高, 服务器能支持更高的并发

		//生产 会阻塞
		strs.transfer("aaa");

		//生产 不会阻塞
		//strs.put("aaa");
		
		//先生产再消费
		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
}
