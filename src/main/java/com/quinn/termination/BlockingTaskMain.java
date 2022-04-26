/**   
* @Title: BlockingTaskMain.java 
* @Package com.quinn.concurrency 
* @Description: TODO 
* @author Quinn
* @date Apr 13, 2022 10:52:49 PM 
* @version 1.0.0   
*/
package com.quinn.termination;

/**
 * @ClassName: BlockingTaskMain
 * @Description: TODO(這裡用一句話描述這個類的作用)
 * @author Quinn
 * @date Apr 13, 2022 10:52:49 PM
 */
public class BlockingTaskMain {
	/**
	 * @Title: main
	 * @Description: TODO(這裡用一句話描述這個方法的作用)
	 * @param @param args 引數說明
	 * @return void 返回型別
	 * @throws
	 */
	public static void main(String[] args) {
		Thread blockingTask = new Thread(new BlockingTask());
		blockingTask.start();
		blockingTask.interrupt();
	}

	public static class BlockingTask implements Runnable {

		@Override
		public void run() {
			try {
				System.out.println("Zzz");
				Thread.sleep(5000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				System.out.println("thread finished !!");
			}
		}
	}
}
