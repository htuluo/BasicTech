package basic.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @description: 演示Semaphore的使用，多线程多资源的强用逻辑
 * @author: luolm
 * @createTime： 2019/10/28
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SemophoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢到车位");
                    Thread.sleep(new Random().nextInt(3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
                System.out.println(Thread.currentThread().getName() + "\t离开车位");
            }, String.valueOf(i)).start();
        }


    }
}
