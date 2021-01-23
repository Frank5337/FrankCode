package com.zbl.concurrent.msb.c_026;

import java.util.concurrent.*;

/**
 * 认识future
 * future代表Callable返回值
 */
public class T06_Future {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		//futureTask impl RunnableFuture impl Runnable
		FutureTask<Integer> task = new FutureTask<>(()->{
			TimeUnit.MILLISECONDS.sleep(500);
			return 1000;
		}); //new Callable () { Integer call();}

		//线程里面执行Callable任务
		new Thread(task).start();
		
		System.out.println(task.get()); //阻塞,  等着任务执行完, get方法拿到的就是futureTask返回的结果
		//要用Callable返回值 得指定类型

		//*******************************
		ExecutorService service = Executors.newFixedThreadPool(5);
		Future<Integer> f = service.submit(()->{
			TimeUnit.MILLISECONDS.sleep(500);
			return 1;
		});
		System.out.println(f.get());
		//是否完成
		System.out.println(f.isDone());
		
	}
}
