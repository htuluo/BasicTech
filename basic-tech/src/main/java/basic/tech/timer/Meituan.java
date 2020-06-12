package basic.tech.timer;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @description: QPS
 * @author: luolm
 * @createTime： 2020/6/10
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class Meituan {

    private static Map<String, Integer> map = new HashMap<>();

    static {
        long time = System.currentTimeMillis() / 1000;
        map.put(String.valueOf(time), 10);
    }

    public static void main(String[] args) {

        Meituan main = new Meituan();
        main.setFunctionName("main");
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Random random = new Random();
                    int i1 = random.nextInt(1000);
                    TimeUnit.MILLISECONDS.sleep(i1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
            map.put(seconds, 20);
            return true;
        }
        Integer integer = map.get(seconds).intValue() - 1;
        Integer value = map.put(seconds, integer);
        System.out.println("key:" + seconds + " before:" + value + " after:" + integer);
        if (value < 0) {
            return false;
        }
        return true;

    }
}
