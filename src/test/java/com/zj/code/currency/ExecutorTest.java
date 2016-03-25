package com.zj.code.currency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExecutorTest {
	
	public static void main(String[] args) {
		ExecutorService executor=Executors.newCachedThreadPool(); 
		Future<String> future=executor.submit(new Callable<String>() {
			public String call() throws Exception {
				System.out.println(System.currentTimeMillis()+"执行一个长时间任务。。。");
				Thread.currentThread().sleep(5000);
				return System.currentTimeMillis()+"执行完毕";
			}
		});
		System.out.println(System.currentTimeMillis()+"主线程继续3秒钟");
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		FutureTask task;
		try {
			System.out.println(future.get()+future.isDone());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} 
		System.out.println(System.currentTimeMillis()+"主线程执行");
	}

}
