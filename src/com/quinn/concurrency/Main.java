/**   
* @Title: Main.java 
* @Package com.quinn.concurrency 
* @Description: TODO 
* @author pigmilk
* @date Apr 12, 2022 12:39:06 PM 
* @version 1.0.0   
*/
package com.quinn.concurrency;

/**
 * @ClassName: Main
 * @Description: TODO(這裡用一句話描述這個類的作用)
 * @author pigmilk
 * @date Apr 12, 2022 12:39:06 PM
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("Hello from: " + Thread.currentThread().getName());
		Thread thread = new Thread(() -> System.out.println("Hello from: " + Thread.currentThread().getName()));
		Thread newThread = new NewThread();
		thread.start();
		newThread.start();
	}

	public static class NewThread extends Thread {
		public void run() {
			System.out.println("Hello from: " + this.getName());
		}

	}
	
	/**
	 * 
	 * 1. secure vault
	 * */

}
