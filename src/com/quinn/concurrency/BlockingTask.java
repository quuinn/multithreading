/**   
* @Title: BlockingTask.java 
* @Package com.quinn.concurrency 
* @Description: TODO 
* @author pigmilk
* @date Apr 13, 2022 10:52:49 PM 
* @version 1.0.0   
*/
package com.quinn.concurrency;

/** 
* @ClassName: BlockingTask 
* @Description: TODO(這裡用一句話描述這個類的作用) 
* @author pigmilk
* @date Apr 13, 2022 10:52:49 PM  
*/
public class BlockingTask implements Runnable{

	/** 
	* @Title: main 
	* @Description: TODO(這裡用一句話描述這個方法的作用) 
	* @param @param args  引數說明 
	* @return void 返回型別 
	* @throws 
	*/
	public static void main(String[] args) {
		Thread blockingTask = new Thread(new BlockingTask());
		blockingTask.start();
		blockingTask.interrupt();
	}


	@Override
	public void run() {
		try {
			System.out.println("Zzz");
			Thread.sleep(5000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			System.out.println("thread finished !!");
		}
	}

}