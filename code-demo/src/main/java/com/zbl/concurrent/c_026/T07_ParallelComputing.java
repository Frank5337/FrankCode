package com.zbl.concurrent.c_026;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池的概念
 * nasa
 *
 * 并行计算 快
 */
public class T07_ParallelComputing {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
//		List<Integer> results = getPrime(1, 200000);
		List<Integer> results = getPrime(1, 350000);
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		
		//final int cpuCoreNum = 4;
		final int cpuCoreNum = 8;

		/**
		 * 固定线程数量的线程池
		 */
		ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);
		
		MyTask t1 = new MyTask(1, 80000); //1-5 5-10 10-15 15-20
		MyTask t2 = new MyTask(80001, 130000);
		MyTask t3 = new MyTask(130001, 170000);
		MyTask t4 = new MyTask(170001, 200000);
		MyTask t5 = new MyTask(200001, 230000);
		MyTask t6 = new MyTask(230001, 260000);
		MyTask t7 = new MyTask(260001, 290000);
		MyTask t8 = new MyTask(320001, 350000);

		Future<List<Integer>> f1 = service.submit(t1);
		Future<List<Integer>> f2 = service.submit(t2);
		Future<List<Integer>> f3 = service.submit(t3);
		Future<List<Integer>> f4 = service.submit(t4);
		Future<List<Integer>> f5 = service.submit(t5);
		Future<List<Integer>> f6 = service.submit(t6);
		Future<List<Integer>> f7 = service.submit(t7);
		Future<List<Integer>> f8 = service.submit(t8);

		start = System.currentTimeMillis();
		f1.get();
		f2.get();
		f3.get();
		f4.get();
		f5.get();
		f6.get();
		f7.get();
		f8.get();
		end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	static class MyTask implements Callable<List<Integer>> {
		int startPos, endPos;
		
		MyTask(int s, int e) {
			this.startPos = s;
			this.endPos = e;
		}
		
		@Override
		public List<Integer> call() throws Exception {
			List<Integer> r = getPrime(startPos, endPos);
			return r;
		}
		
	}

	/**
	 * 传一个数判断是否是质数
	 * @param num
	 * @return
	 */
	static boolean isPrime(int num) {
		for(int i=2; i<=num/2; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}

	/**
	 * 给一个范围
	 * @param start
	 * @param end
	 * @return
	 */
	static List<Integer> getPrime(int start, int end) {
		List<Integer> results = new ArrayList<>();
		for(int i=start; i<=end; i++) {
			if(isPrime(i)) results.add(i);
		}
		
		return results;
	}
}
