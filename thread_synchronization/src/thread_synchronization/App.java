package thread_synchronization;

import java.sql.Date;
import java.sql.Time;

import javax.xml.crypto.Data;

public class App {
	private int count = 0;

	public static void main(String args[]) {
		App app = new App();
		app.worker();
	}

	public synchronized void increment(){
		count++;
	}
	
	public synchronized void decrement(){
		count--;
	}
	
	public void worker() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 1000; i++)
				{	increment();
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 1000; i++)
				{	decrement();
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		
		long start = System.currentTimeMillis();
		System.out.println();
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println(count);
		System.out.println("time taken :" + (end- start));
	}
}
