package com.zbl.msb.concurrent.c_026;

import java.util.concurrent.Executor;

/**
 * 认识Executor
 */
public class T01_MyExecutor implements Executor {

	public static void main(String[] args) {
		new T01_MyExecutor().execute(()->System.out.println("hello executor"));
	}

	@Override
	public void execute(Runnable command) {
		//new Thread(command).run();
		command.run();
	}

}

