package ThreadPackage;

public class MyRunnable implements Runnable {
	@Override
	public void run() {
		System.out.println("thread start,name-" + Thread.currentThread().getName());
	}

	public static void main(String[] args) throws InterruptedException {

		Runnable myThread1 = new MyRunnable();
		Runnable myThread2 = new MyRunnable();
		Thread thread1 = new Thread(myThread1);
		thread1.setName("thread1");
		Thread thread2 = new Thread(myThread2);
		thread2.setName("thread2");
		System.out.println("thread1 is alive:" + thread1.isAlive());
		thread1.start();
		System.out.println("thread1 is alive2:" + thread1.isAlive());
		thread1.interrupt();
		thread1.join();
		System.out.println("thread1 is alive3:" + thread1.isAlive());

		thread2.start();

		while (thread1.isAlive()) {
			thread1.interrupt();
			System.out.println("dds");
		}
		Thread thread3 = new Thread();
		thread3.start();

	}
}