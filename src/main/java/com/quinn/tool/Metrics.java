/**   
* @Title Metrics.java 
* @Package com.quinn.tool 
* @Description TODO 
* @author Quinn
* @date Apr 20, 2022 1:00:02 PM 
* @version 1.0.0   
*/
package com.quinn.tool;

import java.util.Random;

/**
 * @ClassName Metrics
 * @Description TODO(這裡用一句話描述這個類的作用)
 * @author Quinn
 * @date Apr 20, 2022 1:00:02 PM
 */
public class Metrics {
	private long count = 0;
//	private double average = 0.0;
	private volatile double average = 0.0;

	public synchronized void addSample(long sample) {
		double currentSum = average * count;
		count++;
		average = (currentSum + sample) / count;
	}

	public double getAverage() {
		return average;
	}

	public static class BusinessLogic extends Thread {
		private Metrics metrics;
		private Random random = new Random();

		public BusinessLogic(Metrics metrics) {
			this.metrics = metrics;
		}

		public void run() {
			while (true) {
				long start = System.currentTimeMillis();
				try {
					Thread.sleep(random.nextInt(10));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				long end = System.currentTimeMillis();
				metrics.addSample(end - start);
			}
		}
	}

	public static class MetricsPrinter extends Thread {
		private Metrics metrics;

		public MetricsPrinter(Metrics metrics) {
			this.metrics = metrics;
		}
		
		public void run() {
			while(true){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				double currentAverage = metrics.getAverage();
				System.out.println("Current Average is "+ currentAverage);
				
			}
		}
	}

	public static void main(String[] args) {
		Metrics metrics = new Metrics();
		BusinessLogic businessLogicThread1 = new BusinessLogic(metrics);
		BusinessLogic businessLogicThread2 = new BusinessLogic(metrics);
		
		MetricsPrinter metricsPrinter = new MetricsPrinter(metrics);
		businessLogicThread1.start();
		businessLogicThread2.start();
		metricsPrinter.start();
		
	}
}
