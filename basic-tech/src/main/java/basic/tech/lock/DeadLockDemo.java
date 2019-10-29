package basic.tech.lock;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/10/29
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class DeadLockDemo implements Runnable{
    private String lockA ;
    private String lockB ;

    public DeadLockDemo(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 占用" + lockA + "尝试用" + lockB);
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {

                System.out.println(Thread.currentThread().getName() + "\t 占用" + lockB + "尝试用" + lockA);
            }
        }
    }

    public static void main(String[] args) {
        String lockA="lockA";
        String lockB="lockB";
        new Thread(new DeadLockDemo(lockA,lockB), "AAA").start();
        new Thread(new DeadLockDemo(lockB,lockA), "BBB").start();
    }
}
