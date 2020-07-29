package basic.tech.leetcode;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/7/29
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class FindMinNumFromArray {
    public static void main(String[] args) {
        int[] array = new int[]{5, 6, 7, 8, 9, 10,11, 0, 1, 2, 3, 4};
        System.out.println(findMinNumFromArray(array));
    }

    /**
     * 找出最小的数，其中数组是在某个位置做了翻转，如 new int[]{5, 6, 7, 8, 9, 10,11, 0, 1, 2, 3, 4};
     * @param array
     * @return
     */
    public static int findMinNumFromArray(int[] array) {
        if (array.length == 0) {
            throw new RuntimeException("数组为空");

        }

        int left = 0;
        int right = array.length - 1;
        while (array[left] > array[right]) {
            int mid = (left + right) / 2;
            if ((right - left) == 1) {
                System.out.println("1----------");
                return array[right];
            }
            if (array[mid] > array[right]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        System.out.println("2-------------");
        return array[left + 1];
    }
}
