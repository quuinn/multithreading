/**   
* @Title StandardStack.java 
* @Package datastructure 
* @Description TODO 
* @author Quinn
* @date Apr 26, 2022 12:12:07 PM 
* @version 1.0.0   
*/
package datastructure;

/**
 * @ClassName StandardStack
 * @Description TODO(這裡用一句話描述這個類的作用)
 * @author Quinn
 * @date Apr 26, 2022 12:12:07 PM
 */
public class StandardStack<T> {
	private StackNode<T> head;
	private int counter = 0;

	public synchronized void push(T value) {
		StackNode<T> newHead = new StackNode<>(value);
		newHead.next = head;
		head = newHead;
		counter++;
	}

	public synchronized T pop() {
		if (head == null) {
			counter++;
			return null;
		}
		T value = head.value;
		head = head.next;
		counter++;
		return value;
	}

	public int getCounter() {
		return counter;
	}

	private static class StackNode<T> {
		public T value;
		public StackNode<T> next;

		public StackNode(T value) {
			this.value = value;
			this.next = next;
		}
	}
}
