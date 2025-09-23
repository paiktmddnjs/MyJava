package 쓰레드;

public class RunnableDemo {

	public static void main(String[] args) {
		Runnable task = () -> {
			String name = Thread.currentThread().getName();
			System.out.println(name + " 실행 중!");
		};

		Thread t = new Thread(task);
		t.start(); // 새로운 스레드 생성 및 실행
		System.out.println("메인 스레드 실행");
	}
}