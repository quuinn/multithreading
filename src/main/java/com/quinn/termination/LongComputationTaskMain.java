/**   
* @Title: LongComputationTaskMain.java 
* @Package com.quinn.concurrency 
* @Description: TODO 
* @author pigmilk
* @date Apr 14, 2022 10:26:41 PM 
* @version 1.0.0   
*/
package com.quinn.termination;

import java.math.BigInteger;
import java.util.stream.IntStream;

/**
 * @ClassName: LongComputationTaskMain
 * @Description: TODO(這裡用一句話描述這個類的作用)
 * @author pigmilk
 * @date Apr 14, 2022 10:26:41 PM
 */
public class LongComputationTaskMain {
	public static void main(String[] args) throws InterruptedException {
		int iBase = 20000;
		int iPow = 20000;
		Thread thread = new Thread(() -> System.out.print(iBase + "^" + iPow + " = " + pow(iBase, iPow)));
		thread.start();
		Thread.sleep(3000);
		
		// interrupt() 並不會拋出 InterruptedException 
		thread.interrupt();
	}

	private static BigInteger pow(int base, int power) {
		BigInteger result = BigInteger.ONE;
		for (int i = 0; power > i; i++) {
			
			if(Thread.currentThread().isInterrupted()){
				System.out.println("計算第 " + i + " 次. TIME_OUT");
				return BigInteger.ZERO;
			}
			result = result.multiply(new BigInteger(base + ""));
		}
		return result;
	}

}
