package com.zbl.msb.concurrent.c_026;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class T14_ParallelStreamAPI {
	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<>();
		Random r = new Random();
		for(int i=0; i<10000; i++) nums.add(1000000 + r.nextInt(1000000));
		
		//System.out.println(nums);
		
		long start = System.currentTimeMillis();
		nums.forEach(T14_ParallelStreamAPI::isPrime);
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		
		//使用parallel stream api
		
		start = System.currentTimeMillis();
		nums.parallelStream().forEach(T14_ParallelStreamAPI::isPrime);
		end = System.currentTimeMillis();
		
		System.out.println(end - start);
	}
	
	static boolean isPrime(int num) {
		for(int i=2; i<=num/2; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}

	@Test
	public void test01() throws Exception {
		long l = System.currentTimeMillis();
		System.out.println(l);
		Date date = new Date(l / 1000);
		System.out.println(date);

		Date date1 = new Date();
		System.out.println(date1);
		System.out.println(getHourTime(date1));

	}

	public static Date getHourTime(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
//		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		//ca.set(Calendar.MINUTE, ca.get(Calendar.HOUR_OF_DAY));
		date = ca.getTime();
		return date;
	}

	@Test
	public void test02() throws Exception {
		Date date = new Date();
		System.out.println(date);
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.HOUR, -1);
		date = ca.getTime();
		System.out.println(date);

		Calendar ca1 = Calendar.getInstance();
		ca1.add(Calendar.HOUR, -3);
		date = ca1.getTime();
		System.out.println(date);
	}

	@Test
	public void test011() throws Exception {
		String[] split = "".split(",");
		for (int i = 0; i < split.length; i++) {
			System.out.println(1);
		}
	}

	@Test
	public void test21() throws Exception {
		Date dd = new Date();
		System.out.println(dd);
		Calendar ca = Calendar.getInstance();
		ca.setTime(dd);

		//ca.set(Calendar.SECOND, 0);
		dd = ca.getTime();
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime.getSecond());
		System.out.println(dd);
	}

	@Test
	public void test0111() throws Exception {
	    String  a = "1111111122222222111111112222222211111111222222221111111122222222111111112222222211111111222222221111111122222222111111112222222211111111222222221111111122222222111111112222222211111111222222221111111122222222111111112222222211111111222222221111111122222222";
		System.out.println(a.substring(0,255).length());
	}

	@Test
	public void test01111() throws Exception {
		Integer a = null;
		long aa = (long)a;
		System.out.println(aa);
	}

}
