package basic.tech.map;

import basic.tech.atomic.AtomicIntegerDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/7/3
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class MapOneKeyTest {
    //    private static Map<Integer, Integer> map = new HashMap<>();//线程不安全
    private static Map<Integer, Integer> map = new ConcurrentHashMap<>();//线程不安全？？
    private static Map<Integer, AtomicInteger> map2 = new HashMap<>();//线程不安全

    private static int count = 10000;

    static {
        map.put(100, count);
        map2.put(100, new AtomicInteger(count));
    }

    public static void main(String[] args) {
        test2();
    }


    /**
     * 需要对公用对象加锁才能保证线程安全
     */
    public static void test2() {
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                processMap();
                countDownLatch.countDown();

            }).start();

        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.get(100));
        System.out.println(map2.get(100));
    }

    public static void processMap() {
        //synchronized (map) ,,synchronized (map2) ,,
        synchronized (MapOneKeyTest.class) {
            map.put(100, map.get(100) - 1);
            map2.put(100, new AtomicInteger(map2.get(100).decrementAndGet()));
        }
    }

    /**
     * 不加锁
     */
    public static void test1() {
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                map.put(100, map.get(100) - 1);
                map2.put(100, new AtomicInteger(map2.get(100).decrementAndGet()));
                countDownLatch.countDown();

            }).start();

        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.get(100));
        System.out.println(map2.get(100));
    }
}
