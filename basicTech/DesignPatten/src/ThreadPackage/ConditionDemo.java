package ThreadPackage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.security.auth.x500.X500Principal;

/*
 * 	多线程生产，多线程消费
 *
 *
 * */

class StringBuffer {

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

	// 生产线程
	public void put(Object x) throws InterruptedException {
		lock.lock();
		try{
			while(count == arr.length)
				notFull.await();
			arr[count] = x;
			System.err.println("put"+x.toString());
			if (++putper == arr.length) {
				putper = 0;
			}
			count++;
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
			System.err.println("take" + x);
			if (++takeper == arr.length) {
				takeper = 0;
			}
			count--;
			notFull.signal();
			return x;

		} finally {
			lock.unlock();
		}
	}

}

class Put implements Runnable {
	private StringBuffer stringBuffer;
	public Put(StringBuffer stringbuff) {
		this.stringBuffer = stringbuff;
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			try {
				stringBuffer.put(i);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}

class Take implements Runnable {
	private StringBuffer stringBuffer;

	public Take(StringBuffer stringbuff) {

		this.stringBuffer = stringbuff;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				stringBuffer.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

public class ConditionDemo {

	public static void main(String[] args) throws InterruptedException {
		StringBuffer sb = new StringBuffer();

		Put put = new Put(sb);
		Thread t1 = new Thread(put);
		Take take = new Take(sb);
		Thread t2 = new Thread(take);
		t1.start();
		t2.start();


	}

}
