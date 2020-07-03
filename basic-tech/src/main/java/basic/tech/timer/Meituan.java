package basic.tech.timer;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: QPS
 * @author: luolm
 * @createTime： 2020/6/10
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class Meituan {

    private static Map<String, AtomicInteger> map = new HashMap<>();
    private static Map<String, Integer> map2 = new HashMap<>();
    private static int initialValue = 1000;

    static {
        long time = System.currentTimeMillis() / 1000;
        map.put(String.valueOf(time), new AtomicInteger(initialValue));
        map2.put(String.valueOf(time), initialValue);
    }

    public static void main(String[] args) {
        int count_thread = 10000;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count_thread);

        Meituan main = new Meituan();
        main.setFunctionName("main");
        for (int i = 0; i < count_thread; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
//                try {
//                    Random random = new Random();
//                    int i1 = random.nextInt(1000);
//                    TimeUnit.MILLISECONDS.sleep(i1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(main.qps3());

            }).start();
        }
//        boolean b = main.qps2();
//
//        System.out.println(b);
    }

    private ThreadLocal local = new ThreadLocal();

    public String getFunctionName() {
        return local.get().toString();
    }

    public void setFunctionName(String functionName) {
        local.set(functionName);
    }

//    public boolean qps() {
//        String functionName = this.getFunctionName();
//        String seconds = String.valueOf(System.currentTimeMillis() / 1000);
//        if (!map.containsKey(seconds)) {
//            map.clear();
//            map.put(seconds, 20);
//            return true;
//        }
//
//        Integer integer = map.get(seconds).intValue() - 1;
//        Integer value = map.put(seconds, integer);
//        System.out.println("before:"+value+" after:"+integer);
//        if (value < 0) {
//            return false;
//        }
//        return true;
//
//    }

    public boolean qps2() {
        String seconds = String.valueOf(System.currentTimeMillis() / 1000);
        if (!map.containsKey(seconds)) {
            map.put(seconds, new AtomicInteger(initialValue));
            return true;
        }
//        synchronized (map) {
        AtomicInteger atomicInteger = map.get(seconds);
        Integer integer = atomicInteger.getAndDecrement();
//            try {
//                Random random = new Random();
//                int i1 = random.nextInt(100);
//                TimeUnit.MILLISECONDS.sleep(i1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        AtomicInteger value = map.put(seconds, atomicInteger);
        System.out.println("key:" + seconds + " before:" + value.get() + " after:" + integer);
        if (value.get() < 0) {
            return false;
        }
        return true;
//        }

    }

    public boolean qps3() {
        String seconds = String.valueOf(System.currentTimeMillis() / 1000);
        if (!map2.containsKey(seconds)) {
            map2.put(seconds, initialValue);
            return true;
        }
//        synchronized (map) {
        Integer atomicInteger = map2.get(seconds);

//            try {
//                Random random = new Random();
//                int i1 = random.nextInt(100);
//                TimeUnit.MILLISECONDS.sleep(i1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        Integer value = map2.put(seconds, --atomicInteger);
        System.out.println("key:" + seconds + " before:" + value + " after:" + atomicInteger);
        if (value < 0) {
            return false;
        }
        return true;
//        }

    }
}
