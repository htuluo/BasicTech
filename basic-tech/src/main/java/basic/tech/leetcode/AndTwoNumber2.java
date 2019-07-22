package basic.tech.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/7/8
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class AndTwoNumber2 {
    public static void main(String[] args) {
        LinkedList<Integer> first = new LinkedList<>();
        LinkedList<Integer> second = new LinkedList<>();
        first.add(7);
        first.add(2);
        second.add(3);
        second.add(8);
        second.add(9);
        List<Integer> integers = addTwo(first, second);
        System.out.println(integers.toString());

    }

    /**
     * 两个非负链表list的倒叙相加
     * @param first
     * @param second
     * @return
     */
    public static List<Integer> addTwo(List<Integer> first, List<Integer> second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return new LinkedList<Integer>();
        }
        int firstSize = first.size();
        int secondSize = second.size();
        List<Integer> sum = new LinkedList<>();
        int minSize = firstSize <= secondSize ? firstSize : secondSize;
        List<Integer> minList;
        List<Integer> maxList;
        if (firstSize <= secondSize) {
            minList=first;
            maxList = second;
        } else {
            minList=second;
            maxList = first;
        }
        boolean needUp = false;
        for (int i = 0; i < minSize; i++) {
            int sumOne = minList.get(i) + maxList.get(i);
            sumOne = (needUp ? sumOne+1 : sumOne);
            if (sumOne >= 10) {
                sum.add(sumOne - 10);
                needUp = true;
            } else {
                sum.add(sumOne);
                needUp = false;
            }

        }



        for (int i = minSize; i < maxList.size(); i++) {
            int sumOne = maxList.get(i);
            sumOne = (needUp ? sumOne+1 : sumOne);
            if (sumOne >= 10) {
                sum.add(sumOne - 10);
                needUp = true;
            } else {
                sum.add(sumOne);
                needUp = false;
            }
        }
        if (needUp) {
            sum.add(1);
        }


        return sum;

    }
}
