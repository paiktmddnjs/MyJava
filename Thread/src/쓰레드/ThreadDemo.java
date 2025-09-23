package 쓰레드;

class ThreadDemo extends Thread {
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name + " 실행 중!");
	}
}

public class ThreadDemo {
	public static void main(String[] args) {
		ThreadDemo t1 = new ThreadDemo();
		ThreadDemo t2 = new ThreadDemo();

		t1.start();
		t2.start();
		System.out.println("메인 스레드 실행");
	}
}