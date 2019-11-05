package basic.tech.concurrent;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

/**
 * @description: SynchronousQueue的使用，put和 take必须交替完成
 * @author: luolm
 * @createTime： 2019/10/28
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SychronizeQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                for (int i = 0; i < 30; i++) {
                    System.out.println(Thread.currentThread().getName() + "\tput\t" + i);
                    queue.put(String.valueOf(i));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AA").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 30; i++) {
                    System.out.println(Thread.currentThread().getName() + "\ttake\t" + queue.take());

                    int bound = 1000;
                    try {
                        Thread.sleep(new Random().nextInt(bound));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
}
