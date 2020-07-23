package basic.tech.map;

import java.util.*;
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
        ExecutorService newFixedThreadPool = new ThreadPoolExecutor(200, 200, 20L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10),new ThreadPoolExecutor.CallerRunsPolicy());
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
//        for (int i = 0; i < 10; i++) {
////            doit();
//            conCurrentHashMap();
//            Thread.sleep(500L);
//        }
//        System.out.println("===========");

        mapSort();
    }

    public static void mapSort() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a",9);
        map.put("b",4);
        map.put("c",6);
        map.put("c",100);
        map.put("c",80);
        map.put("c",12);
        int max=0;
        Optional<Map.Entry<String, Integer>> first = map.entrySet().parallelStream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).findFirst();
        System.out.println(first.get().getKey());
    }

}
