package basic.tech.concurrent;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

/**
 * @description: SynchronousQueue的使用，
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
                queue.put("a");

                queue.put("b");
                queue.put("c");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AA").start();
        new Thread(() -> {
            try {
                System.out.println(queue.take());

                int bound = 5000;
                try {
                    Thread.sleep(new Random().nextInt(bound));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(queue.take());
                try {
                    Thread.sleep(new Random().nextInt(bound));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(queue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
}
