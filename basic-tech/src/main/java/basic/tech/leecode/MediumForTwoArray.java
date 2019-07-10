package basic.tech.leecode;

import org.apache.tools.ant.taskdefs.optional.script.ScriptDef;

/**
 * 第四题
 * @description: 要求时间复杂度O(log(n+m))
 * @author: luolm
 * @createTime： 2019/7/10
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class MediumForTwoArray {
    public static void main(String[] args) {
        int [] a1=new int[]{};
        int [] a2=new int[]{3,4};
        System.out.println(findMedium(a1,a2));
    }

    /**
     * 两个有序数组的合并，时间复杂度O(n+m)
     * @param arr1
     * @param arr2
     * @return
     */
    public static double findMedium(int[] arr1, int[] arr2) {
        int[] mergeSort = mergeSort(arr1, arr2);
        int length = arr1.length + arr2.length;
        printArray(mergeSort);
        if (length % 2 == 1) {
            return mergeSort[length / 2 ];
        } else {
            return (double) (mergeSort[length / 2-1] + mergeSort[length / 2 ]) / 2;
        }

    }
    public static  void  printArray(int[] arr){
        for (int a:arr){
            System.out.print(a+",");
        }
        System.out.println();
    }

    public static int[] mergeSort(int[] r1, int[] r2) {
        if (r1.length == 0) {
            return r2;
        }
        if (r2.length == 0) {
            return r1;
        }
        int[] result = new int[r1.length + r2.length];
        int k = 0;
        int i = 0;
        int j = 0;

        while (i < r1.length) {
            if (j >= r2.length) {
                result[k++] = r1[i];
                i++;
                continue;
            }
            if (r1[i] > r2[j]) {
                result[k++] = r2[j];
                j++;
            } else {
                result[k++] = r1[i];
                i++;
            }

        }
        for (; j < r2.length; j++) {
            result[k++] = r2[j];
        }
        return result;

    }

}
