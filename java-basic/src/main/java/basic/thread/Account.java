package basic.thread;

public class Account {

	private int balance;

	public Account(int balance) {
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public synchronized void add(int num) {
		balance = balance + num;
	}

	public synchronized void withdraw(int num) {
		balance = balance - num;
	}

	public static void main(String[] args) throws InterruptedException {
		Account account = new Account(1000);
		Thread a = new Thread(new AddThread(account, 20), "add");
		Thread b = new Thread(new WithdrawThread(account, 20), "withdraw");
		a.start();
		b.start();
		a.join();
		b.join();
		System.out.println(account.getBalance());
	}

	static class AddThread implements Runnable {
		Account account;
		int amount;

		public AddThread(Account account, int amount) {
			this.account = account;
			this.amount = amount;
		}

		@Override
		public void run() {
				for (int i = 0; i < 200000; i++) {
					account.add(amount);
				}

		}
	}

	static class WithdrawThread implements Runnable {
		Account account;
		int amount;

		public WithdrawThread(Account account, int amount) {
			this.account = account;
			this.amount = amount;
		}

		@Override
		public void run() {
				for (int i = 0; i < 100000; i++) {
				account.withdraw(amount);
			}

		}
	}
}
