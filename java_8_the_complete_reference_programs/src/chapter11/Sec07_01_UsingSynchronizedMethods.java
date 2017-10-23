package chapter11;

public class Sec07_01_UsingSynchronizedMethods {

	public static void main(String[] args) {
		
		CallMe target = new CallMe();
		
		new Caller(target, "hello");
		new Caller(target, "iam");
		new Caller(target, "vishal");
		
	}

}

class CallMe {
	
	synchronized void call(String msg){
		System.out.print("[" + msg);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		System.out.println("]");
	}
	
}

class Caller implements Runnable {

	private Thread t;
	private CallMe target;
	private String msg;

	Caller(CallMe target, String msg){
		this.target = target;
		this.msg = msg;
		t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		target.call(msg);		
	}
	
}









