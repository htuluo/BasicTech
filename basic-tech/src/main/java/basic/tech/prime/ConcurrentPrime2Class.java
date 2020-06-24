package basic.tech.prime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @description: 查找小于某数的所有素数
 * @author: luolm
 * @createTime： 2020/6/22
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ConcurrentPrime2Class {

    /**
     * 缓存已处理的数的素数列表
     */
    private static List<Integer> LIST_CACHE= new ArrayList<>();
    /**
     * 缓存最大处理过的数
     */
    private static Integer MAX_NUM=2;

    private static ReentrantLock lock = new ReentrantLock();

    /**
     * 查找某个正整数的所有质数
     *
     * @param num
     * @return
     */
    public List<Integer> findPrimeForNum(int num) {
        List<Integer> list = new ArrayList<>();
        if (num < 2) {
            return list;
        }
        //先从缓存list中查找
        if (!LIST_CACHE.isEmpty() && LIST_CACHE.size() != 0 && num <= MAX_NUM) {
            list = LIST_CACHE.stream().filter(item ->
                    Integer.compare((int) item, num) <= 0
            ).collect(Collectors.toList());
            return list;
        }

        lock.lock();
        try {
            LIST_CACHE = this.eulerPrimeFindList(num);
            MAX_NUM = num;
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            lock.unlock();
        }

        return LIST_CACHE;
    }

    /**
     * 欧拉筛选
     *
     * @param num
     * @return
     */
    public List<Integer> eulerPrimeFindList(int num) {
        // flagArr[i]存储标志过的合数；prime数组保存所有质数
        boolean[] flagArr = new boolean[num];
        List<Integer> primeList = new ArrayList<>();
        int count = 0;  // 质数数量
        for (int i = 2; i < num; i++) {
            if (!flagArr[i]) {
                primeList.add(count++, i);
            }
            // 剔除已筛选的素数的倍数
            for (int j = 0; j < count; j++) {
                if (i * primeList.get(j) >= num) {
                    break;
                }
                flagArr[i * primeList.get(j)] = true;
                //之前标记过不再处理
                if (i % primeList.get(j) == 0) {
                    break;
                }
            }
        }
        return primeList;
    }


}
