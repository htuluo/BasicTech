package basic.tech.thread;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/6/28
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class StaticLockTest {
    private static Integer at = 0;
    private Integer bt = 0;
    private Integer ct = 560;
    private String dt = " 560";

    /**
     * 锁静态资源，会造成竞争
     */
    public void print() {
        synchronized (at) {
            System.out.println(Thread.currentThread().getName() + " is printing");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 锁对象资源，如果是每个线程都申请新对象，会造成竞争
     * 如果公用对象，不会竞争
     */
    public void print2() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " is printing");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * integer值小于128，是同一个常量池里的对象，会造成竞争
     */
    public void print3() {
        synchronized (this.bt) {
            System.out.println(Thread.currentThread().getName() + " is printing");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 大于128的integer，会在堆中申请，不同对象的时候不会锁竞争
     */
    public void print4() {
        synchronized (this.ct) {
            System.out.println(Thread.currentThread().getName() + " is printing");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 同小于128的integer，有常量池，会锁竞争
     */
    public void print5() {
        synchronized (this.dt) {
            System.out.println(Thread.currentThread().getName() + " is printing");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                StaticLockTest staticLockTest = new StaticLockTest();
//                System.out.println(staticLockTest.hashCode());
                staticLockTest.print5();
            }, String.valueOf(i)).start();

        }
    }
}
