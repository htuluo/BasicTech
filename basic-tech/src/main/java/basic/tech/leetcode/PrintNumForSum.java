package basic.tech.leetcode;

/**
 * @description: 打印n位数 和
 * @author: luolm
 * @createTime： 2020/6/20
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class PrintNumForSum {
    public static void main(String[] args) {
        PrintNumForSum printNumForSum = new PrintNumForSum();
        printNumForSum.printNume(10, 3);

    }

    private void printNume(int sum, int count) {
        StringBuilder sb = new StringBuilder();

        while (count > 0) {
            sb = oneLevel(sb, sum, count--);
            System.out.println(sb.toString());
        }

    }

    private StringBuilder oneLevel(StringBuilder stringBuilder, int sum, int count) {
        int sumByBuilder = getSumByBuilder(stringBuilder);
        for (int i = 0; i < sum - sumByBuilder; i++) {
            stringBuilder.append(String.valueOf(i));
            oneLevel(stringBuilder, sum - sumByBuilder, count);
        }
        return stringBuilder;

    }

    private int getSumByBuilder(StringBuilder builder) {
        int result = 0;
        for (int i = 0; i < builder.length(); i++) {
            result += Integer.valueOf(String.valueOf(builder.charAt(i)));
        }
        return result;
    }
}
