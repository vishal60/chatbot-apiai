package chapter11;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sec07_02_UsingSynchronizedBlock {

	public static void main(String[] args) throws InterruptedException {

		Scanner scanner = new Scanner(System.in);

		List<Caller2> callerList = new ArrayList<Caller2>();

		CallMe2 target = new CallMe2();

		String word;

		do {
			word = scanner.nextLine();
			if (word.equals("eof")) break;
			callerList.add(new Caller2(target, word));
		} while (true);
		
		scanner.close();

		for (Caller2 c : callerList)
			c.start();

		for (Caller2 c : callerList)
			c.t.join();

		System.out.println("\nMain thread exiting");
	}

}

class CallMe2 {

	void call(String msg) {
		System.out.print("[" + msg);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		System.out.println("]");
	}

}

class Caller2 implements Runnable {

	Thread t;
	private CallMe2 target;
	private String msg;

	Caller2(CallMe2 target, String msg) {
		this.target = target;
		this.msg = msg;
		t = new Thread(this);
	}

	@Override
	public void run() {
		synchronized (target) {
			target.call(msg);
		}
	}

	void start() {
		t.start();
	}

}
