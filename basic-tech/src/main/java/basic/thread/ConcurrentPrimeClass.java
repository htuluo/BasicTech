package basic.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @description: 查找小于某数的所有素数
 * @author: luolm
 * @createTime： 2020/6/22
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ConcurrentPrimeClass {

    /**
     * 缓存已处理的数的素数列表
     */
    private static CopyOnWriteArrayList LIST_CACHE;
    /**
     * 缓存最大处理过的数
     */
    private static Integer MAX_NUM;

    private static ReentrantLock lock = new ReentrantLock();

    static {
        LIST_CACHE = new CopyOnWriteArrayList<Integer>();
        MAX_NUM = 2;
    }

    /**
     * 判断某整数是否为质数
     *
     * @param num
     * @return
     */
    public boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        int count = (int) Math.sqrt(num) + 1;
        for (int i = 2; i < count; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 查找某个正整数的所有质数
     *
     * @param num
     * @return
     */
    public List<Integer> findPrimeForNum(int num) {
        List<Integer> list = new LinkedList<>();
        if (num < 2) {
            return list;
        }
        //先从缓存list中查找
        if (!LIST_CACHE.isEmpty() && LIST_CACHE.size() != 0 && num < MAX_NUM) {
            list = (ArrayList) LIST_CACHE.parallelStream().filter(item ->
                    Integer.compare((int) item, num) <= 0
            ).collect(Collectors.toList());
            return list;
        }


        lock.lock();
        try {
            int start = 0;
            if (LIST_CACHE.isEmpty() || LIST_CACHE.size() == 0) {
                start = 2;
            } else {
                start = (int) LIST_CACHE.get(LIST_CACHE.size() - 1) + 1;
            }
            for (int i = start; i <= num; i++) {
                if (isPrime(i)) {
                    LIST_CACHE.add(i);
                }
            }
            if (MAX_NUM < num) {

                MAX_NUM = num;
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            lock.unlock();
        }

        return LIST_CACHE;
    }

}
