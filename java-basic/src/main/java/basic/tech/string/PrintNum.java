package basic.tech.string;

import javax.xml.transform.Source;
import java.io.IOException;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/23
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class PrintNum {
    public static void main(String[] args) {
        try {
            int n=3;
            if (n<0){
                System.out.println("n cannot be negative");
                return;
            }
            Print1ToMaxOfNDigits_2(n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Print1ToMaxOfNDigits_2(int n) {

        if (n <= 0) {
            return;
        }
        StringBuffer number = new StringBuffer();

        for (int i = 0; i < n; i++) {
            number.append('0');

        }

        while (!Increment(number)) {
            PrintNumber(number);
        }
    }

    public static boolean Increment(StringBuffer s) {
        boolean isOverflow = false;
        int nTakeOver = 0;
        int nLength = s.length();
        for (int i = nLength - 1; i >= 0; i--) {
            int nSum = s.charAt(i) - '0' + nTakeOver;
            if (i == nLength - 1) {
                nSum++;
            }
            if (nSum >= 10) {
                if (i == 0) {
                    isOverflow = true;

                } else {
                    nSum -= 10;
                    nTakeOver = 1;
                    s.setCharAt(i, (char) ('0' + nSum));
                }

            } else {
                s.setCharAt(i, (char) ('0' + nSum));
                break;
            }
        }
        return isOverflow;
    }
    public static void PrintNumber(StringBuffer s){
        boolean isBeginning0 = true;
        for(int i = 0; i < s.length(); i++){
            if(isBeginning0 && s.charAt(i) != '0'){
                isBeginning0 = false;
            }
            if(!isBeginning0){
                System.out.print(s.charAt(i));
            }
        }

        System.out.println();
    }

}
