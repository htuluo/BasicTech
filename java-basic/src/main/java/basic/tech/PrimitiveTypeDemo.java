package basic.tech;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/7/4
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class PrimitiveTypeDemo {
    public static void main(String[] args) {
        short short1=1;
        short1=(short)( short1+1);
        short1+=1;
        System.out.println("short1==="+short1);
        int a=456;
        byte b=(byte)a;
        System.out.println("byte b=="+b);
        boolean bbl=true;

        int a3=3;
        Integer a2=new Integer(3);
        Integer a4=new Integer(3);
        System.out.println(a3==a2);
        System.out.println(a4==a2);

        String str2="abc";
        String str1=new String("abc");
        String str3= "abc";
        System.out.println(str1==str2);
        System.out.println(str3==str2);

        System.out.println("------------");
        Integer i1=100;
        Integer i2=100;
        System.out.println(i1==i2);
        Integer i3=128;
        Integer i4=128;
        System.out.println(i3==i4);
        System.out.println("------===------");

        String s1=new StringBuilder("go").append("od").toString();
        String s2=new StringBuilder("ja").append("va").toString();

        System.out.println(s1.intern()==s1);
        System.out.println(s1.intern()==s1);
    }
}
