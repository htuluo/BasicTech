package basic.tech.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 线程的定向唤醒，与synchronize的区别演示
 * @author: luolm
 * @createTime： 2019/10/28
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 * <p>
 * 要求ABC三个线程顺序打印（A打印五次，B打印10次，C打印15次），10轮
 * <p>
 * 判断
 * 干活
 * 唤醒
 */
public class SynchronizeReentrantLockDemo1 {
    private int number = 1;//A:1;B:2;C:3
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            while (number != 1) {
                condition1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            lock.unlock();

        }
    }

    public static void main(String[] args) {
        SynchronizeReentrantLockDemo1 synchronizeReentrantLockDemo1 = new SynchronizeReentrantLockDemo1();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronizeReentrantLockDemo1.print5();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronizeReentrantLockDemo1.print10();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronizeReentrantLockDemo1.print15();
            }
        }, "C").start();
    }
}
