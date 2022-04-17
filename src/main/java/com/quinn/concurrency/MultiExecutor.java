/**   
* @Title: MultiExecutor.java 
* @Package com.quinn.concurrency 
* @Description: TODO 
* @author pigmilk
* @date Apr 13, 2022 9:32:12 PM 
* @version 1.0.0   
*/
package com.quinn.concurrency;

/** 
* @ClassName: MultiExecutor 
* @Description: TODO(這裡用一句話描述這個類的作用) 
* @author pigmilk
* @date Apr 13, 2022 9:32:12 PM  
*/
import java.util.List;

public class MultiExecutor {

	// Add any necessary member variables here
	private List<Runnable> tasks;

	/*
	 * @param tasks to executed concurrently
	 */
	public MultiExecutor(List<Runnable> tasks) {
		this.tasks = tasks;
	}

	/**
	 * Starts and executes all the tasks concurrently
	 */
	public void executeAll() {
		tasks.forEach(t -> new Thread(t).start());
	}
}