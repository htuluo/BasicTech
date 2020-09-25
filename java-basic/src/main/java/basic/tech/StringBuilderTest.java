package basic.tech;

import java.util.Collections;

/**
 * Created by dell-3020 on 2017/9/7.
 */
public class StringBuilderTest {
    public static void main(String[] args) {
        StringBuilder sb=new StringBuilder("dd");
        sb.append(9);
        System.out.println(sb.toString());
        String a= "bbb";
        String b=new String("bbb");
        System.out.println(a==b);
        System.out.println(a.equals(b));
    }
}
