package basic.tech.prime;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 寻找素数
 * @author: luolm
 * @createTime： 2020/6/22
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class PrimeClass {
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
        for (int i = 2; i <= num; i++) {
            if (isPrime(i)) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 查找某个正整数的所有质数
     *
     * @param num
     * @return
     */
    public Set<Integer> findPrimeSetForNum(int num) {
        Set<Integer> set = new HashSet<>();
        if (num < 2) {
            return set;
        }
        for (int i = 2; i <= num; i++) {
            if (isPrime(i)) {
                set.add(i);
            }
        }
        return set;
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


    public static void main(String[] args) {
        PrimeClass primeClass = new PrimeClass();
        long start = System.currentTimeMillis();
//        Set<Integer> primeList = primeClass.findPrimeSetForNum(100000000);
        List<Integer> primeList = primeClass.findPrimeForNum(100000000);
        System.out.println("cost:" + (System.currentTimeMillis() - start) / 1000);
        start = System.currentTimeMillis();
        int filter = 4000000;
        System.out.println(primeList.stream().filter(item -> item < filter).collect(Collectors.toList()).size());
        System.out.println("stream cost:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        System.out.println(primeList.parallelStream().filter(item -> item < filter).collect(Collectors.toList()).size());
        System.out.println("parallelStream cost:" + (System.currentTimeMillis() - start));

    }


}
