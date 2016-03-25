/**
 * 
 */
package com.zj.code.currency;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

/**
 * @author Administrator
 *
 */
public class My {
	@Test
	public void test(){
			final CountDownLatch count=new CountDownLatch(2);
			for(int i=0;i<4;i++){
				final int j=i;
				new Thread(new Runnable(){
					@Override
					public void run() {
						try {
							Thread.currentThread().sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if(j<2){
							System.out.println("准备好的"+j);
							count.countDown();
						}else{
							try {
								count.await();
								System.out.println("ok"+j);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				},"myThread"+i).start();
			}
			try {
				count.await();
				System.out.println("ok");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
	}
}
