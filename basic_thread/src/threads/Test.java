package threads;

import java.util.Scanner;

class App implements Runnable{
	private volatile int count = 100;
	private volatile boolean running = true;
	@Override
	public void run() {
		while(running){
			count++;
			System.out.println("current count is: " + count);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void updateCount(){
		count = 20;
		running = false;
		System.out.println("count updated");
	}
	
	public void printCount(){
		System.out.println("count value is : "+ count);
	}
}

public class Test {
	public static void main(String args[]){
		App a = new App();
		App b = new App();
		
		Thread t1 = new Thread(a);
		//Thread t2 = new Thread(b);
		
		t1.start();
		//t2.start();
		
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		a.updateCount();
		try {
			t1.join();
			//t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a.printCount();
	}
}