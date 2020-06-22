package basic.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/6/22
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class PrimeClass {
    /**
     *  判断某整数是否为质数
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
     * @param num
     * @return
     */
    public List<Integer> findPrimeForNum(int num) {
        List<Integer> list = new LinkedList<>();
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

    public static void main(String[] args) {
        PrimeClass primeClass = new PrimeClass();
        System.out.println(primeClass.findPrimeForNum(200));
    }



}
