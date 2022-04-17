/**   
* @Title JoinAndRaceConditionMain.java 
* @Package com.quinn.termination 
* @Description TODO 
* @author pigmilk
* @date Apr 16, 2022 9:02:39 PM 
* @version 1.0.0   
*/
package com.quinn.racecondition;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName JoinAndRaceConditionMain
 * @Description 使用 join 來處理 race condition 的問題
 * @author pigmilk
 * @date Apr 16, 2022 9:02:39 PM
 */
public class JoinAndRaceConditionMain {

	/**
	 * @ClassName FactoralThread
	 * @Description TODO(這裡用一句話描述這個類的作用)
	 * @author pigmilk
	 * @date Apr 16, 2022 9:03:47 PM
	 */
	public static class FactoralThread extends Thread {
		private long inputNumber;
		private BigInteger result = BigInteger.ZERO;
		private boolean isFinished = false;

		public FactoralThread(long inpuntNumber) {
			this.inputNumber = inpuntNumber;
		}

		public BigInteger factorial(long n) {
			BigInteger tempResult = BigInteger.ONE;

			for (long i = n; i > 0; i--) {
				tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));

				
			}
			System.out.println("calc end:" + Thread.currentThread().getName());
			return tempResult;
		}

		public void run() {
			this.result = factorial(inputNumber);
			this.isFinished = true;
		}

		public boolean isFinished() {
			return isFinished;
		}

		public BigInteger getResult() {
			return result;
		}

	}

	/**
	 * @Title main
	 * @Description TODO(這裡用一句話描述這個方法的作用)
	 * @param @param args 引數說明
	 * @return void 返回型別
	 * @throws InterruptedException
	 * @throws
	 */
	public static void main(String[] args) throws InterruptedException {
		List<Long> inputNumbers = Arrays.asList(0L, 3435L, 352L, 2324L, 4656L, 23L, 5566L);		
		List<FactoralThread> threads = new ArrayList<>();

		for (Long inputNumber : inputNumbers) {
			threads.add(new FactoralThread(inputNumber));

		}
		for (Thread thread : threads) {
			thread.start();
			System.out.println("Main Thread: " + thread.getName() + " string !");
		}
		// 注意這裡是 main thread, 只會各執行緒都秀一次資訊
		for (int i = 0; i < inputNumbers.size(); i++) {
			FactoralThread factorialThread = threads.get(i);
			System.out.println("Main Thread: check " + factorialThread.getName());
			if (factorialThread.isFinished) {
				System.out.println("Main Thread: Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
			} else {
				System.out.println("Main Thread: The calculation for " + inputNumbers.get(i) + " is still in progress");
			}
		}
		for (Thread thread : threads) {
			thread.join();
			System.out.println("Main Thread: " + thread.getName() + ", JOIN !");
		}
		
		System.out.println("Main Thread: Main method end");
	}

}
