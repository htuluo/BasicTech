package basic.tech.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/10/28
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class CasLockDemo {
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t come in");
        while (!atomicReference.compareAndSet(null, thread)) {
            ;
        }
        System.out.println(thread.getName() + "\t get lock");
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) {
        CasLockDemo casLockDemo = new CasLockDemo();

        new Thread(() -> {
            casLockDemo.myLock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            casLockDemo.myUnLock();

        }, "AA").start();
        new Thread(() -> {
            casLockDemo.myLock();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            casLockDemo.myUnLock();

        }, "BB").start();

    }
}
