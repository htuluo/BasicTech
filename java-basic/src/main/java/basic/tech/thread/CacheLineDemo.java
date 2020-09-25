package basic.tech.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 缓存行在volatile使用时demo，加入一些缓存数据防止缓存行被频繁换出缓存
 * @author: luolm
 * @createTime： 2020/6/29
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class CacheLineDemo {
    private static long count = 1_0000_0000L;

    @sun.misc.Contended ///-XX:-RestrictContended
    static class T {
        private volatile long x;

        public T() {
        }
    }
    private static T[] arr=new T[2];
    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread thread = new Thread(() -> {
            for (long i = 0; i < count; i++) {
                arr[0].x = i;
            }
            countDownLatch.countDown();
        });
        Thread thread1 = new Thread(() -> {
            for (long i = 0; i < count; i++) {
                arr[1].x = i;
            }
            countDownLatch.countDown();
        });
        long start = System.currentTimeMillis();
        thread.start();
        thread1.start();
        countDownLatch.await();
        System.out.println(System.currentTimeMillis() - start);


    }
}
