package ThreadPackage;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.security.auth.x500.X500Principal;

/*
 * 	多线程生产，多线程消费
 *
 *
 * */

class StringBuffer2 {

	// 创建锁对象
	final Lock lock = new ReentrantLock();

	// 分别创建2个监视器
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();

	// 创建资源 数组形式
	final Object[] arr = new Object[100];

	// 定义数组指针
	int putper;
	int takeper;

	// 定义计数器
	int count;

//	public StringBuffer s;

	// 生产线程
	public void put(Object x) throws InterruptedException {
		lock.lock();
		try{
			while(count == arr.length)
				notFull.await();
			arr[count] = x;
			System.err.println(Thread.currentThread().getName()+"put"+x.toString());
			System.out.println("-------------");
			System.out.println(arr);
			if (++putper == arr.length) {
				putper = 0;
			}
			++count;
			notEmpty.signal();
		}finally {
			lock.unlock();
		}

	}

	// 消费线程
	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while(count == 0)
				notEmpty.await();
			Object x = arr[takeper];
			System.err.println(Thread.currentThread().getName()+"take"+x.toString());
			System.out.println(arr);
			if (++takeper == arr.length) {
				takeper = 0;
			}
			--count;
			notFull.signal();
			return x;

		} finally {
			lock.unlock();
		}
	}
}

class Prod extends StringBuffer implements Runnable {
	private StringBuffer s;
	public Prod(StringBuffer s) {
		this.s = s;
	}

	@Override
	public void run() {
		for (int i = 0; i < arr.length; i++) {
			try {
				put(i);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

}

class Cus extends StringBuffer implements Runnable {
	private StringBuffer s;
	public Cus(StringBuffer s) {
		this.s = s;
	}

	@Override
	public void run() {
		try {
			if (count!=0) {
				System.out.println(take());
			}

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

}

public class ConditionDemo2 {

	public static void main(String[] args) throws InterruptedException {
		StringBuffer s = new StringBuffer();
		Prod pro = new Prod(s);
		Cus cus = new Cus(s);

		Thread t1 = new Thread(pro);
		Thread t2 = new Thread(pro);
		Thread t3 = new Thread(cus);
		Thread t4 = new Thread(cus);

		t1.start();
		t2.start();
		t3.start();
		t4.start();

	}

}
