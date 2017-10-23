package chapter11;

public class Sec08_01_DeadLock implements Runnable {

	private A a = new A();
	private B b = new B();

	Sec08_01_DeadLock() {

		Thread.currentThread().setName("Main Thread");
		Thread t = new Thread(this, "Racing Thread");
		t.start();

		a.methodInA(b);
	}

	public static void main(String[] args) {

		new Sec08_01_DeadLock();

	}

	@Override
	public void run() {
		b.methodInB(a);

	}

}

class A {

	synchronized void methodInA(B b) {

		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " entered A.methodInA()");

		System.out.println("Trying to call B.last()");
		b.last();

	}

	synchronized void last() {
		System.out.println("Inside A.last()");
	}

}

class B {

	synchronized void methodInB(A a) {

		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " entered B.methodInB()");
		
		System.out.println("Trying to call A.last()");
		a.last();
	}

	synchronized void last() {
		System.out.println("Inside B.last()");
	}
}