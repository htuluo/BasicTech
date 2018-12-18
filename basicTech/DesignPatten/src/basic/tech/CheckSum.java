package basic.tech;

import java.text.MessageFormat;

/**
 * Created by dell-3020 on 2017/9/28.
 */
public class CheckSum {


    public static void main(String[] args) {
        int a,b,c,d;
            c=1;

        for (d=0;d<10;d++){
            b=2*d+8;
            a=d+9;
            if((100*a+10*b+a)+(100*c+10*d+a)==(1000*1+100*d+10*a+b)){
                System.out.println(MessageFormat.format("a={0},b={1},c={2},d={3}",a,b,c,d));
                break;
            }
        }

    }
}
