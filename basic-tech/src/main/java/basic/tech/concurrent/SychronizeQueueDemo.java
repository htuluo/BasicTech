package basic.tech.concurrent;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/10/28
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SychronizeQueueDemo {
    public static void main(String[] args) {
        priorityBlockQueueDemo();
    }

    /**
     * priorityBlockQueueDemo使用，是一个排序队列
     */
    private static void priorityBlockQueueDemo() {
        BlockingQueue<Integer> queue=new PriorityBlockingQueue<>();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    int i1 = new Random().nextInt(5000);
                    System.out.println(Thread.currentThread().getName() + "\tput\t" + i1);
                    queue.put(Integer.valueOf(i1));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "CC").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 15; i++) {
                    System.out.println(Thread.currentThread().getName() + "\ttake\t" + queue.poll());

                    int bound = 1000;
                    try {
                        Thread.sleep(new Random().nextInt(bound));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "DD").start();
    }

    /**
     * SynchronousQueue的使用，put和 take必须交替完成
     */
    private static void synchronizeQueueDemo() {
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
