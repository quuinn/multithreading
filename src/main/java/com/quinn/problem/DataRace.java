/**   
* @Title DataRace.java 
* @Package com.quinn.problem 
* @Description TODO 
* @author pigmilk
* @date Apr 21, 2022 12:12:29 AM 
* @version 1.0.0   
*/
package com.quinn.problem;

/**
 * @ClassName DataRace
 * @Description TODO(這裡用一句話描述這個類的作用)
 * @author pigmilk
 * @date Apr 21, 2022 12:12:29 AM
 */
public class DataRace {
	int x = 0;
	int y = 0;

	void add() {
		x++;
		y++;
	}

	void checkValue() {
		if (y > x) {
			System.out.println("error: Data Race !!");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		DataRace dr = new DataRace();
		Thread t1 = new Thread(() -> {
			while (true) {
				dr.add();
			}
		});
		Thread t2 = new Thread(() -> {
			while (true) {
				dr.checkValue();
			}
		});
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}
}
