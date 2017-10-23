package chapter11;

public class Sec03_01_ImplementingRunnable {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Main Thread is running...");

		Thread.sleep(2000);

		System.out.println("Creating a Thread...");
		Thread.sleep(2000);
		new FirstThread("My First Thread");

		Thread mainThread = Thread.currentThread();

		for (int i = 5; i > 0; i--) {
			System.out.println("Thread: " + mainThread.getName() + ": " + i);
			Thread.sleep(1000);
		}
		
		System.out.println(mainThread.getName() + " is terminating...");
		
	}

}

class FirstThread implements Runnable {

	private Thread t;

	FirstThread(String threadName) {
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

}
