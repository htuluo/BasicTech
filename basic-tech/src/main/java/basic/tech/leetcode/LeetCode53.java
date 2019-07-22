package basic.tech.leetcode;

import java.text.MessageFormat;
import java.util.Arrays;

/**
 * @description: 连续子集和最大
 * @author: luolm
 * @createTime： 2019/7/22
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class LeetCode53 {
    public static void main(String[] args) {
        int[] array = {1,-2,3};
        findSubArray(array);
    }

    public static void findSubArray(int[] array) {
        int startIndex = 0, endIndex = 0;
        int sum = 0, tmpSum = 0;
        for (int i = 0; i < array.length; i++) {
            tmpSum += array[i];
            if (sum<tmpSum){
                endIndex=i;
                sum=tmpSum;
            }
            int inSum=tmpSum;
            for (int j = 1; j <= i; j++) {
                inSum-=array[j-1];
                if (sum < inSum) {
                    sum = inSum;
                    startIndex = j;
                }

            }
        }

        System.out.println(Arrays.toString(array));
        System.out.println(MessageFormat.format("maxSum={0},starIndex={1},endIndex={2}",sum,startIndex,endIndex));
        System.out.println(Arrays.toString(Arrays.copyOfRange(array,startIndex,endIndex+1)));
    }
}
