/**   
* @Title: Main.java 
* @Package com.quinn.concurrency 
* @Description: TODO 
* @author pigmilk
* @date Apr 12, 2022 12:39:06 PM 
* @version 1.0.0   
*/
package com.quinn.concurrency;

/**
 * @ClassName: Main
 * @Description: TODO(這裡用一句話描述這個類的作用)
 * @author pigmilk
 * @date Apr 12, 2022 12:39:06 PM
 */
public class Main {
	public static void main(String[] args) {

	}

	private static class Vault {
		private int password;

		public Vault(int password) {
			this.password = password;
		}

		public boolean isCorrectPassword(int guess) {

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return this.password == guess;
		}
	}

	private static abstract class HackerThread extends Thread {
		protected Vault vault;

		public HackerThread(Vault vault) {
			this.vault = vault;
			this.setName(this.getClass().getSimpleName());
			this.setPriority(MAX_PRIORITY);
		}
	}

}
