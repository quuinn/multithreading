/**   
* @Title MinMaxMetrics.java 
* @Package com.quinn.tool 
* @Description TODO 
* @author Quinn
* @date Apr 20, 2022 10:02:32 PM 
* @version 1.0.0   
*/
package com.quinn.tool;

/**
 * @ClassName MinMaxMetrics
 * @Description TODO(這裡用一句話描述這個類的作用)
 * @author Quinn
 * @date Apr 20, 2022 10:02:32 PM
 */
public class MinMaxMetrics {

	// Add all necessary member variables
	private volatile long min;
	private volatile long max;

	/**
	 * Initializes all member variables
	 */
	public MinMaxMetrics() {
		// Add code here
		this.max = Long.MIN_VALUE;
		this.min = Long.MAX_VALUE;
	}

	/**
	 * Adds a new sample to our metrics.
	 */
	public void addSample(long newSample) {
		// Add code here
		synchronized (this) {
			this.min = Math.min(newSample, this.min);
			this.max = Math.max(newSample, this.max);
		}
	}

	/**
	 * Returns the smallest sample we've seen so far.
	 */
	public long getMin() {
		// Add code here
		return this.min;
	}

	/**
	 * Returns the biggest sample we've seen so far.
	 */
	public long getMax() {
		// Add code here
		return this.max;
	}

}
