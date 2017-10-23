package chapter11;

public class Sec09_00_SuspendingResumingStopping {

	public static void main(String[] args) throws InterruptedException {
		
		MyThread t1 = new MyThread("My Thread");
		
		Thread.sleep(1000);
		t1.mySuspend();
		Thread.sleep(2000);
		t1.myResume();
		
		t1.t.join();
		
		System.out.println("exiting...");
	}
}

class MyThread implements Runnable {

	Thread t;
	private boolean suspendFlag;

	MyThread(String threadName) {
		t = new Thread(this, threadName);
		suspendFlag = false;
		t.start();
		System.out.println("Thread " + threadName + " is starting...");
	}

	@Override
	public void run() {

		try {
			for (int i = 15; i > 0; i--) {
				System.out.println("count: " + i);
				Thread.sleep(200);

				synchronized (this) {
					while (suspendFlag) {
						wait();
					}
				}

			}
		} catch (InterruptedException e) {
		}

	}

	synchronized void mySuspend() {
		suspendFlag = true;
	}

	synchronized void myResume() {
		suspendFlag = false;
		notify();
	}

}
