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

    static {
        long time = System.currentTimeMillis() / 1000;
        map.put(String.valueOf(time), new AtomicInteger(20));
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10000);

        Meituan main = new Meituan();
        main.setFunctionName("main");
        for (int i = 0; i < 10000; i++) {
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
                System.out.println(main.qps2());

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
            map.put(seconds, new AtomicInteger(20));
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
}
