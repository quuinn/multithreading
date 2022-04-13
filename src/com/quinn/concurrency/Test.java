package com.quinn.concurrency;
import javax.management.RuntimeErrorException;

/**   
* @Title: Test.java 
* @Package  
* @Description: TODO 
* @author pigmilk
* @date Apr 9, 2022 8:56:50 AM 
* @version 1.0.0   
*/

/**
 * @ClassName: Test
 * @Description: TODO(這裡用一句話描述這個類的作用)
 * @author pigmilk
 * @date Apr 9, 2022 8:56:50 AM
 */
public class Test {
	public static void main(String[] args) throws InterruptedException {
//		Thread thread_l = new Thread(() -> {
//		});

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("進入新執行緒: " + Thread.currentThread().getName());
				System.out.println("進入新執行緒, 優先權: " + Thread.currentThread().getPriority());
				throw new RuntimeException("!!!!");
			}
		});

//		thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//
//			@Override
//			public void uncaughtException(Thread t, Throwable e) {
//				System.out.println("" + t.getName() + "\n" + "" + e.getMessage());
//
//			}
//		});

		thread.setUncaughtExceptionHandler(
				(Thread t, Throwable e) -> System.out.println("" + t.getName() + "\n" + "" + e.getMessage()));
		
		thread.start();

	}
}
