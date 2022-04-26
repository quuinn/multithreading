/**   
* @Title LockFreeStack.java 
* @Package datastructure 
* @Description TODO 
* @author Quinn
* @date Apr 26, 2022 12:22:29 PM 
* @version 1.0.0   
*/
package datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName LockFreeStack
 * @Description TODO(這裡用一句話描述這個類的作用)
 * @author Quinn
 * @date Apr 26, 2022 12:22:29 PM
 */
public class LockFreeStack<T> {

	public static boolean useAtomic = true;

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

	private AtomicReference<StackNode<T>> head = new AtomicReference<>();
	private AtomicInteger counter = new AtomicInteger(0);

	public void push(T value) {
		StackNode<T> newHeadNode = new StackNode<>(value);
		while (true) {
			StackNode<T> currentHeadNode = head.get();
			newHeadNode.next = currentHeadNode;
			if (head.compareAndSet(currentHeadNode, newHeadNode)) {
				break;
			} else {
				LockSupport.parkNanos(1);
			}
		}
		counter.incrementAndGet();
	}

	public T pop() {
		StackNode<T> currentHeadNode = head.get();
		StackNode<T> newHeadNode;
		while (currentHeadNode != null) {
			newHeadNode = currentHeadNode.next;
			if (head.compareAndSet(currentHeadNode, newHeadNode)) {
				break;
			} else {
				LockSupport.parkNanos(1);
				currentHeadNode = head.get();
			}

		}
		counter.incrementAndGet();
		return currentHeadNode != null ? currentHeadNode.value : null;
	}

	public int getCounter() {
		return counter.get();
	}

	public static class StackNode<T> {
		public T value;
		public StackNode<T> next;

		public StackNode(T value) {
			this.value = value;
			this.next = next;
		}
	}

}
