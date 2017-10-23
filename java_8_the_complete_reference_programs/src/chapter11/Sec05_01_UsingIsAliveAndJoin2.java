package chapter11;

public class Sec05_01_UsingIsAliveAndJoin2 {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Main Thread is running...");

		Thread.sleep(2000);

		System.out.println("Creating Threads...");
		Thread.sleep(2000);
		NThread2 mt1 = new NThread2("one", 2);
		NThread2 mt2 = new NThread2("two", 8);
		NThread2 mt3 = new NThread2("three", 4);

		System.out.println("Thread " + mt1.getThread().getName() + " is alive? :" + mt1.getThread().isAlive());
		System.out.println("Thread " + mt2.getThread().getName() + " is alive? :" + mt2.getThread().isAlive());
		System.out.println("Thread " + mt3.getThread().getName() + " is alive? :" + mt3.getThread().isAlive());

		mt1.getThread().join();
		System.out.println("thread one returned".toUpperCase());
		mt2.getThread().join();
		System.out.println("thread two returned".toUpperCase());
		mt3.getThread().join();
		System.out.println("thread three returned".toUpperCase());

		Thread.sleep(1000);
		System.out.println("Main Thread is terminating...");

	}

}

class NThread2 implements Runnable {

	private Thread t;
	private int threadRunTime;

	NThread2(String threadName, int threadRunTime) throws InterruptedException {
		t = new Thread(this, threadName);
		this.threadRunTime = threadRunTime;
		t.start();
		System.out.println("Thread: " + t.getName() + " created.");
	}

	@Override
	public void run() {

		System.out.println("Thread: " + t.getName() + " is running");

		try {
			Thread.sleep(1000 * threadRunTime);
		} catch (InterruptedException e) {
		}

		System.out.println("Thread: " + t.getName() + " is stopping");

	}

	public Thread getThread() {
		return t;
	}
}
