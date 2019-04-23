package basic.tech.string;

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
            int n=2;
            if (n<0){
                System.out.println("n cannot be negative");
                return;
            }
            for (int i=0;i<n;i++){
                StringBuilder sb=new StringBuilder();
                printOne(sb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder printOne(StringBuilder sb) {
        for (int j=0;j<10;j++){
            System.out.println(j);
        }
        return sb;
    }
}
