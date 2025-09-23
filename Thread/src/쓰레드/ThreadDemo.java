package 쓰레드;

class MyThread extends Thread {
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " 실행 중!");
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start();
        t2.start();

        System.out.println("메인 스레드 실행");
    }
}
