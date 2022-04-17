/**   
* @Title ComplexCalculationMain.java 
* @Package com.quinn.concurrency 
* @Description TODO 
* @author pigmilk
* @date Apr 17, 2022 6:38:46 AM 
* @version 1.0.0   
*/
package com.quinn.concurrency;

/** 
* @ClassName ComplexCalculationMain 
* @Description TODO(這裡用一句話描述這個類的作用) 
* @author pigmilk
* @date Apr 17, 2022 6:38:46 AM  
*/
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ComplexCalculationMain {
	public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
		BigInteger result = BigInteger.ZERO;
		/*
		 * Calculate result = ( base1 ^ power1 ) + (base2 ^ power2). Where each
		 * calculation in (..) is calculated on a different thread
		 */
		PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
		PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);
		List<PowerCalculatingThread> threads = new ArrayList<PowerCalculatingThread>();
		threads.add(thread1);
		threads.add(thread2);

		// 2. Thread start
		for (Thread thread : threads) {
			thread.start();
			System.out.println(thread.getName() + " : started !");
		}
		// 3. Thread join
		for (Thread thread : threads) {
			try {
				thread.join();
				System.out.println(thread.getName() + " : join !");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (PowerCalculatingThread thread : threads) {
			result = result.add(thread.getResult());
		}
		return result;
	}

	private static class PowerCalculatingThread extends Thread {
		private BigInteger result = BigInteger.ONE;
		private BigInteger base;
		private BigInteger power;

		public PowerCalculatingThread(BigInteger base, BigInteger power) {
			this.base = base;
			this.power = power;
		}

		@Override
		public void run() {
			/*
			 * Implement the calculation of result = base ^ power
			 */

			while (power.compareTo(BigInteger.ZERO) != 0) {
				power = power.subtract(BigInteger.ONE);
				result = result.multiply(new BigInteger(base + ""));
			}
		}

		public BigInteger getResult() {
			return result;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// 1. Thread create
		ComplexCalculationMain main = new ComplexCalculationMain();
		BigInteger base1 = new BigInteger("2");
		BigInteger power1 = new BigInteger("10");
		BigInteger base2 = new BigInteger("3");
		BigInteger power2 = new BigInteger("10");

		BigInteger result = new ComplexCalculationMain().calculateResult(base1, power1, base2, power2);

		System.out.println("Main thread: END, result = " + result);
	}
}