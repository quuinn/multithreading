/**   
* @Title CheckThreads.java 
* @Package com.quinn.tool 
* @Description TODO 
* @author pigmilk
* @date Apr 17, 2022 1:06:39 AM 
* @version 1.0.0   
*/
package com.quinn.tool;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
/** 
* @ClassName CheckThreads 
* @Description 測試機器可以 create 多少個 thread
* @author pigmilk
* @date Apr 17, 2022 1:06:39 AM  
*/
	public class CheckThreads {
	    public static void main(String [] args) {
	        AtomicInteger counter = new AtomicInteger(0);
	        while(true){
	            new Thread(() -> {
	                System.out.println(counter.incrementAndGet());
	                try {
	                    Thread.sleep(Duration.ofHours(1).toMillis());
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }).start();
	        }
	    }
	} 

