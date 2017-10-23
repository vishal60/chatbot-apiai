package chapter11;

public class Sec05_01_UsingIsAliveAndJoin1 {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Main Thread is running...");

		Thread.sleep(2000);

		System.out.println("Creating Threads...");
		Thread.sleep(2000);
		NThread mt1 = new NThread("one");
		NThread mt2 = new NThread("two");
		NThread mt3 = new NThread("three");
		
		System.out.println("Thread " + mt1.getThread().getName() + " is alive? :" + mt1.getThread().isAlive());
		System.out.println("Thread " + mt2.getThread().getName() + " is alive? :" + mt2.getThread().isAlive());
		System.out.println("Thread " + mt3.getThread().getName() + " is alive? :" + mt3.getThread().isAlive());
		
		mt1.getThread().join();
		mt2.getThread().join();
		mt3.getThread().join();
				
		System.out.println("Main Thread is terminating...");
		
	}

}

class NThread implements Runnable {

	private Thread t;

	NThread(String threadName) throws InterruptedException {
		t = new Thread(this, threadName);
		t.start();
		System.out.println("Thread: " + t.getName() + " created.");
	}

	@Override
	public void run() {

		System.out.println("Thread: " + t.getName() + " is running");

		for (int i = 5; i > 0; i--) {
			System.out.println("Thread: " + t.getName() + ": " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}

		System.out.println("Thread: " + t.getName() + " is stopping");

	}
	
	public Thread getThread() {
		return t;
	}
}
