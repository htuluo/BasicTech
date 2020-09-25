package basic.tech.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 打印n位数 和
 * @author: luolm
 * @createTime： 2020/6/20
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class PrintNumForSum {
    public static void main(String[] args) {
//        PrintNumForSum printNumForSum = new PrintNumForSum();
//        printNumForSum.printNume(10, 3);
        printNume(9, 3);


    }

    private static void printNume(int sum, int count) {
        StringBuilder sb = new StringBuilder();

        ArrayList<String> arrayList = new ArrayList<>();
        oneLevel(arrayList, sb, sum, count);
        System.out.println(arrayList);

    }

    private static void oneLevel(List<String> list, StringBuilder stringBuilder, int sum, int count) {
        while (count > 2) {

            for (int i = 0; i < 8; i++) {
                int j = sum - i;
                if (j >= 0) {
                    stringBuilder.append(String.valueOf(i));

                    oneLevel(list, stringBuilder, j, --count);
                    stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
                } else {
                    continue;
                }
            }
        }
        if (count == 2) {
            for (int i = 0; i < 8; i++) {
                int j = sum - i;
                if (j < 0 || j > 7) {
                    continue;
                }
                stringBuilder.append(String.valueOf(i)).append(String.valueOf(j));
                list.add(stringBuilder.toString());
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
//
            }
        }

    }

    private int getSumByBuilder(StringBuilder builder) {
        int result = 0;
        for (int i = 0; i < builder.length(); i++) {
            result += Integer.valueOf(String.valueOf(builder.charAt(i)));
        }
        return result;
    }


    public static void printSubForSum(int n) {
        int[][] sum = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            sum[i][1] = 1;
            //最开始都为1
        }
        for (int i = 1; i < n + 1; i++) {
            //接下来至少一个为2
            for (int j = 2; j < i + 1; j++) {
                sum[i][j] = sum[i - 1][j - 1] + sum[i - j][j];
            }
            int s = 0;
            for (int j = 1; j < n + 1; j++) {
                s += sum[n][j];
                System.out.println(s);
            }

        }
    }

}
