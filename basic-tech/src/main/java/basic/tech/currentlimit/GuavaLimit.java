package basic.tech.currentlimit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/8/16
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class GuavaLimit {
    public static void main(String[] args) {
        test1();

    }

    /**
     * 单线程获取,匀速等待
     * 0.0
     * 0.198583
     * 0.197858
     * 0.199289
     *
     */
    public static void test1() {
        RateLimiter limiter = RateLimiter.create(5);
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
    }

    /**
     * 多线程获取，递增等待
     * 0.0
     * 0.153641
     * 0.353624
     * 0.5536
     * 0.753584
     * 0.953524
     * 1.153506
     * 1.353497
     * 1.553487
     * 1.753443
     */
    public static void test2() {
        RateLimiter limiter = RateLimiter.create(5);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                System.out.println(limiter.acquire(1));
            });
            thread.start();
        }
    }
}
