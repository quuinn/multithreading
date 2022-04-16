/**   
* @Title: DaemonMain.java 
* @Package com.quinn.termination 
* @Description: TODO 
* @author pigmilk
* @date Apr 15, 2022 12:55:59 PM 
* @version 1.0.0   
*/
package com.quinn.termination;

import java.io.IOException;

/**
 * @ClassName DaemonMain
 * @Description Daemon thread 原先設計是為了要協助 User thread, <br>
 *               因此當所有的 User thread 都結束時, <br>
 *               所有的 Daemon thread 也會跟著 JVM 一起結束
 * @author pigmilk
 * @date Apr 15, 2022 12:55:59 PM
 */
public class DaemonMain {
	public static void main(String[] args) throws InterruptedException {
		String threadName;
		boolean isDaemon;
		threadName = Thread.currentThread().getName();
		isDaemon = Thread.currentThread().isDaemon();

		// main thread 的 daemon 預設為 true
		System.out.println("Thread :" + threadName + "\n" + "isDaemon :" + isDaemon);

		Thread thread = new Thread(new WaitingForUserInput());
		thread.setName("InputWaitingThread");
		threadName = thread.getName();
		isDaemon = thread.isDaemon();

		// new Thread() 物件的 daemon 預設為 false
		System.out.println("Thread :" + threadName + "\n" + "isDaemon :" + isDaemon);

		thread.setDaemon(true);
		thread.start();
		Thread.sleep(2000);

	}

	private static class WaitingForUserInput implements Runnable {
		@Override
		public void run() {
			try {
				while (true) {
					char input = (char) System.in.read();
					if (input == 'q') {
						return;
					}
				}
			} catch (IOException e) {
				System.out.println("An exception was caught " + e);
			}
			;
		}
	}

}
