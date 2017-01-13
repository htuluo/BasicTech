package basicTech;

public class ThreadDeadLock {
	public static class SynAddRunable implements Runnable {
		int a, b;

		public SynAddRunable(int a, int b) {
			this.a = a;
			this.b = b;

		}

		@Override
		public void run() {
			synchronized (Integer.valueOf(a)) {
				synchronized (Integer.valueOf(b)) {
					System.out.println(a + b);

				}

			}

		}

	}

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			new Thread(new SynAddRunable(1, 2)).start();
			new Thread(new SynAddRunable(2, 1)).start();
		}
		System.out.println("dasdf");

		// TODO Auto-generated method stub

	}

}
