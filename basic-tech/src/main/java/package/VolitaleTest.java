package ThreadPackage;

public class VolitaleTest extends Thread {
	public static Integer nInteger = 0;

	public synchronized void inc() {
		nInteger = nInteger + 1;
	}

	public synchronized void run() {
		try {
			for (int i = 0; i < 10; i++) {
				inc();
				sleep(3);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[100];
		for (int i = 0; i < 100; i++) {
			VolitaleTest volitaleTest = new VolitaleTest();
			threads[i] = volitaleTest;
			// threads[i].start();
			// threads[i].join();
		}
		for (int i = 0; i < 100; i++) {
			threads[i].start();
		}
		for (int i = 0; i < 100; i++) {
			threads[i].join();
		}
		System.out.println(VolitaleTest.nInteger);


	}
}
