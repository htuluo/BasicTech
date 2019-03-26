package basic.thread;

import java.lang.StringBuffer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
    final Object[] arr = new Object[30];

    // 定义数组指针
    int putper;
    int takeper;

    // 定义计数器
    int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //	public StringBuffer s;

    // 生产线程
    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == arr.length) {

                notFull.await();
            }
            arr[putper] = x;
            System.err.println(Thread.currentThread().getName() + "：put:" + x.toString()+";count="+count);
            System.out.println("-------------");
//            System.out.println(arr);
            if (++putper == arr.length) {
                putper = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }

    }

    // 消费线程
    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object x = arr[takeper];
            System.err.println(Thread.currentThread().getName() + "：take:" + x.toString());
//            System.out.println(arr);
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

class Prod extends StringBuffer2 implements Runnable {
    private StringBuffer2 s;

    public StringBuffer2 getS() {
        return s;
    }

    public void setS(StringBuffer2 s) {
        this.s = s;
    }

    public Prod(StringBuffer2 s) {
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

class Cus extends StringBuffer2 implements Runnable {
    private StringBuffer2 s;

    public StringBuffer2 getS() {
        return s;
    }

    public void setS(StringBuffer2 s) {
        this.s = s;
    }

    public Cus(StringBuffer2 s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            while (true) {
                take();
                Thread.sleep(200);
            }
//            if (count != 0) {
//                System.out.println();
//            }

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

}

public class ConditionDemo2 {

    public static void main(String[] args) throws InterruptedException {
//        java.lang.StringBuffer s = new java.lang.StringBuffer();
        StringBuffer2 s = new StringBuffer2();
        Prod pro = new Prod(s);
        Cus cus = new Cus(s);

        pro.setCount(20);
        pro.getS().setCount(15);
        System.out.println("cus.getCount()---"+cus.getS().getCount());

        Thread t1 = new Thread(pro);
//        Thread t2 = new Thread(pro);
//        Thread t3 = new Thread(cus);
        Thread t4 = new Thread(cus);

        t1.start();
        t4.start();
//        Thread.sleep(100);
//        t3.start();
//        t2.start();

    }

}
