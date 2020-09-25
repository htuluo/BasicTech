package basic.tech.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 最长递增子序列的个数
 * @author: luolm
 * @createTime： 2019/7/22
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class LeetCode673 {
    public static void main(String[] args) {
        int[] array = {0, 1, 2, 3, 0, 5, 4, 8, 7, 9};
//        System.out.println(findLongerIncreSubArray(array, 0));
        ArrayList<Integer> list = new ArrayList<>();
        findLongerIncreSubArray(array,0, list);
        System.out.println(list.size());
    }

    /**
     * 递归实现子序列的最大长度
     * @param array
     * @param start
     * @return
     */
    public static int findLongerIncreSubArray(int[] array, int start) {
        int maxLength = 0, maxCount = 0;
        if (start == array.length - 1) {
            return 1;
        }
        if (array[start] < array[start + 1]) {
            return 1 + findLongerIncreSubArray(array, start + 1);
        } else {
            return findLongerIncreSubArray(array, start + 1);
        }

    }

    public static int findLongerIncreSubArray(int[] array, int start, List<Integer> list) {
        if (start == array.length - 1) {
            return 1;
        }
        if (array[start] < array[start + 1]) {
            list.add(array[start]);
            return 1 + findLongerIncreSubArray(array, start + 1,list);
        } else {
            return findLongerIncreSubArray(array, start + 1,list);
        }

    }
}
