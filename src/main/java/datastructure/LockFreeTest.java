package datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** 
* @author pigmilk
* @date Apr 28, 2022 10:01:10 PM 
*/
public class LockFreeTest {
	public static void main(String[] arge) throws InterruptedException {
		Random random = new Random();

			LockFreeStack<Integer> stack = new LockFreeStack<>();
//			StandardStack<Integer> stack = new StandardStack<>();
		
		for (int i = 0; i < 100000; i++) {
			stack.push(random.nextInt());
		}
		List<Thread> threads = new ArrayList<>();
		int pushingThreads = 2;
		int poppingThreads = 2;

		for (int i = 0; i < pushingThreads; i++) {
			Thread thread = new Thread(() -> {
				while (true) {
					stack.push(random.nextInt());
				}
			});
			thread.setDaemon(true);
			threads.add(thread);
		}

		for (int i = 0; i < poppingThreads; i++) {
			Thread thread = new Thread(() -> {
				while (true) {
					stack.pop();
				}
			});
			thread.setDaemon(true);
			threads.add(thread);
		}

		for (Thread thread : threads) {
			thread.start();
		}
		Thread.sleep(10000);
		System.out.println(String.format("%,d operations were performed in 10 seconds ", stack.getCounter()));
	}

}
