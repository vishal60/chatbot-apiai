package chapter11;

public class Sec08_00_InterThreadCommunication {

	public static void main(String[] args) throws InterruptedException {

		Q queue = new Q();

		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue, producer);

		producer.t.join();
		System.out.println("producer alive? : " + producer.t.isAlive());

		consumer.t.join();
		System.out.println("consumer alive? : " + consumer.t.isAlive());
		
		System.out.println("exiting...");
		
	}

}

class Q {

	int n;
	boolean valueSet = false;

	synchronized void put(int i) {

		while (valueSet) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		n = i;
		valueSet = true;
		System.out.println("Put: " + n);
		notify();

	}

	synchronized void get() {
		
		while (!valueSet) {
			try {
				long timeout = System.currentTimeMillis();
				wait(2000);
				if (System.currentTimeMillis() - timeout > 2000l) break;
				
			} catch (InterruptedException e) {
			}
		}

		System.out.println("Get: " + n);
		valueSet = false;
		notify();

	}
	
}

class Producer implements Runnable {

	private Q queue;
	int i;
	Thread t;

	Producer(Q queue) {
		this.queue = queue;
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (++i <= 10) {
			queue.put(i);
		}
	}

}

class Consumer implements Runnable {

	private Q queue;
	int i;
	Thread t;
	Producer producer;

	Consumer(Q queue, Producer producer) {
		this.producer = producer;
		this.queue = queue;
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (producer.t.isAlive()) {
			queue.get();
		}
	}

}