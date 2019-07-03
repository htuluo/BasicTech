package basic.tech.string;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/23
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class PrintNum0 {
    public static void main(String[] args) {
        try {
            int n = 5;
            if (n <=0) {
                System.out.println("n cannot be negative");
                return;
            }
            Print1ToMaxOfNDigits_2(n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Print1ToMaxOfNDigits_2(int n) {
        String[] array = new String[n];
        for (int i = 0; i < n; i++) {
            array[i] = "0";
        }
        while (!incrementOne(array)) {
            printArrayToStr(array);
        }
    }

    /**
     * 自增一次
     * @param array
     * @return
     */
    private static boolean incrementOne(String[] array) {
        boolean overFlow = false;
        int n = array.length;
        for (int i = n - 1; i >= 0; i--) {
            int addValue = Integer.parseInt(array[i]) + 1;
            boolean needUpFlag = false;

            if (addValue < 10) {
                array[i] = String.valueOf(addValue);
                return overFlow;
            } else {
                needUpFlag = true;
                if (i == 0) {
                    return true;
                } else {
                    array[i] = "0";
                    for (int j = i - 1; j >= 0; j--) {
                        if (needUpFlag) {
                            addValue = Integer.parseInt(array[j]) + 1;
                        }

                        if (addValue < 10) {
                            array[j] = String.valueOf(addValue);
                            return overFlow;
                        } else {
                            if (j == 0) {
                                overFlow = true;
                                return overFlow;
                            }
                            array[j] = "0";
                            needUpFlag = true;
                            continue;
                        }
                    }
                }
            }
            return overFlow;
        }


        return overFlow;
    }


    /**
     * 打印一次数
     * @param array
     */
    private static void printArrayToStr(String[] array) {
        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder();
        boolean begin0 = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("0")) {
                if (begin0) {
                    continue;
                } else {
                    stringBuilder.append(array[i]);

                }
            } else {
                begin0 = false;
                stringBuilder.append(array[i]);
            }

        }
        if (stringBuilder.toString().length() == 0) {
            stringBuilder.append("0");
        }
        System.out.println(stringBuilder.toString());
    }
}
