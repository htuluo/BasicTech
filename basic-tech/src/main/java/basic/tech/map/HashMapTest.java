package basic.tech.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/6/12
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class HashMapTest {
    static void doit() throws Exception {
        final int count = 400;
        final AtomicInteger checkNum = new AtomicInteger(0);
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(100);
        //
        final Map<Long, String> map = new HashMap<Long, String>();
        map.put(0L, "test");
        //map.put(1L, "www.imxylz.cn");
        for (int j = 0; j < count; j++) {
            newFixedThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    map.put(System.nanoTime() + new Random().nextLong(), "test");
                    String obj = map.get(0L);
                    if (obj == null) {
                        checkNum.incrementAndGet();
                    }
                }
            });
        }
        newFixedThreadPool.awaitTermination(1, TimeUnit.SECONDS);
        newFixedThreadPool.shutdown();

        System.out.println(checkNum.get());
    }

    static void conCurrentHashMap() throws Exception {
        final int count = 400;
        final AtomicInteger checkNum = new AtomicInteger(0);
        ExecutorService newFixedThreadPool = new ThreadPoolExecutor(200, 200, 20L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(200));
        //
        final Map<Long, String> map = new ConcurrentHashMap<>();
        map.put(0L, "test");
        //map.put(1L, "www.imxylz.cn");
        for (int j = 0; j < count; j++) {
            newFixedThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    map.put(System.nanoTime() + new Random().nextLong(), "test");
                    String obj = map.get(0L);
                    if (obj == null) {
                        checkNum.incrementAndGet();
                    }
                }
            });
        }
        newFixedThreadPool.awaitTermination(1, TimeUnit.SECONDS);
        newFixedThreadPool.shutdown();

        System.out.println(checkNum.get());
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
//            doit();
            conCurrentHashMap();
            Thread.sleep(500L);
        }
    }

}
