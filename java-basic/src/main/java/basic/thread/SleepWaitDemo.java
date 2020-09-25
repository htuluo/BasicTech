package basic.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/7/3
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SleepWaitDemo {
    private ReentrantLock lock = new ReentrantLock();

    public void method() {
        synchronized (this) {
            try {
                System.out.println("111111111");
                this.wait();//wait会让出锁
                System.out.println("000000000");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void methodNotify() {
        synchronized (this) {
            try {
                Thread.sleep(1000);
                System.out.println("22222222");
                this.notify();
                System.out.println("33333333333");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * //wait只能放同步方法块当中，lock里不行，会报 java.lang.IllegalMonitorStateException错误
     */
    public void method2() {
        lock.lock();
        try {
            System.out.println("111111111");
            this.wait();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        SleepWaitDemo sleepWaitDemo = new SleepWaitDemo();
        new Thread(() -> {
            sleepWaitDemo.method();
        }).start();

        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sleepWaitDemo.methodNotify();
        }).start();
    }
}
