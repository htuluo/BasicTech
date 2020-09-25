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


    /**
     * 埃式筛选
     *
     * @param n
     * @return
     */
    public int[] eratosthenesPrimeFind(int n) {
        boolean[] flagArr = new boolean[n];
        int[] prime = new int[n];  // flagArr[i]是i的最小质因子；prime数组保存所有质数
        int count = 0;  // 质数数量
        for (int i = 2; i < n; i++) {
            if (!flagArr[i]) {
                prime[count++] = i;
            }  // i是质数
            // 剔除 indexI 的倍数
            for (int indexJ = i + i; indexJ < n; indexJ += i) {
                flagArr[indexJ] = true;
            }
        }
        return prime;
    }

    /**
     * 欧拉筛选
     *
     * @param n
     * @return
     */
    public int[] eulerPrimeFind(int n) {
        boolean[] flagArr = new boolean[n];
        int[] prime = new int[n];  // flagArr[i]是i的最小质因子；prime数组保存所有质数
        int count = 0;  // 质数数量
        for (int i = 2; i < n; i++) {
            if (!flagArr[i]) {
                prime[count++] = i;
            }  // i是质数
            // 剔除 i 的倍数
            for (int j = 0; j < count; j++) {
                if (i * prime[j] >= n) {
                    break;
                }
                flagArr[i * prime[j]] = true;
                if (i % prime[j] == 0) {
                    break;
                }
            }
        }
        return prime;
    }

    /**
     * 欧拉筛选
     *
     * @param num
     * @return
     */
    public List<Integer> eulerPrimeFindList(int num) {
        // flagArr[i]存储标志；primeList保存所有质数
        boolean[] flagArr = new boolean[num];
        List<Integer> primeList = new ArrayList<>();
        int count = 0;  // 质数数量
        for (int i = 2; i < num; i++) {
            if (!flagArr[i]) {
                primeList.add(count++, i);
            }
            //剔除 i 的倍数
            for (int j = 0; j < count; j++) {
                if (i * primeList.get(j) >= num) {
                    break;
                }
                flagArr[i * primeList.get(j)] = true;
                if (i % primeList.get(j) == 0) {
                    break;
                }
            }
        }
        return primeList;
    }

    public static void main(String[] args) {
        PrimeClass primeClass = new PrimeClass();
        long start = System.currentTimeMillis();
//        Set<Integer> primeList = primeClass.findPrimeSetForNum(100000000);
//        List<Integer> primeList = primeClass.findPrimeForNum(100000000);
//        int[] ints = primeClass.eulerPrimeFind(100000000);
        List<Integer> primeList = primeClass.eulerPrimeFindList(100000000);
        System.out.println("cost:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        int filter = 50000000;
        System.out.println(primeList.stream().filter(item -> item < filter).collect(Collectors.toList()).size());
        System.out.println("stream cost:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        System.out.println(primeList.parallelStream().filter(item -> item < filter).collect(Collectors.toList()).size());
        System.out.println("parallelStream cost:" + (System.currentTimeMillis() - start));

    }


}
