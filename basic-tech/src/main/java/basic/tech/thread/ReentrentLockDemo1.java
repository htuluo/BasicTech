package basic.tech.thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 测试多线程可重入锁、死锁
 * @author: luolm
 * @createTime： 2019/50/28
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ReentrentLockDemo1 {
    private Lock lock = new ReentrantLock();

    public void method1() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "\tinvoked the method1");
        method2();
        lock.unlock();
    }

    public void method2() {
        lock.lock();
        try {
            Thread.sleep(new Random().nextInt(50));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\tinvoked the method2====");
//        method1();
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentrentLockDemo1 reentrentLockDemo1 = new ReentrentLockDemo1();
        for (int i = 0; i < 50; i++) {

            new Thread(() -> {
                try {
                    Thread.sleep(new Random().nextInt(50));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrentLockDemo1.method1();


            }, String.valueOf(i) + "-group1").start();
        }
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                reentrentLockDemo1.method2();
            }, String.valueOf(i) + "-group2").start();
        }


    }
}
