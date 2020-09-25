package basic.tech.classinit;

import java.text.MessageFormat;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/10/29
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ReferenceTransferDemo {
    private String str = "good";
    private char[] chars = {'a', 'b', 'c'};
    private int anInt = 3;

    public void change(String a, char[] chars, int anInt) {
        str = "change";
        chars[0] = 'k';
        this.anInt = 9;
    }

    public void change(String str){
        str="bb";
    }

    public static void main(String[] args) {
        //虚拟机能使用的最大内存
        long l = Runtime.getRuntime().maxMemory();
        //虚拟机中的内存
        long l1 = Runtime.getRuntime().totalMemory();
        System.out.println(MessageFormat.format("max:{0},total:{1}",l/1024/1024,l1/1024/1024));
        ReferenceTransferDemo referenceTransferDemo = new ReferenceTransferDemo();
        System.out.println(referenceTransferDemo.str + " " + String.valueOf(referenceTransferDemo.chars) + " " + referenceTransferDemo.anInt);
        referenceTransferDemo.change(referenceTransferDemo.str, referenceTransferDemo.chars, referenceTransferDemo.anInt);
        System.out.println(referenceTransferDemo.str + " " + String.valueOf(referenceTransferDemo.chars + " " + referenceTransferDemo.anInt));
        String stra="bbbs";
        referenceTransferDemo.change(stra);
        System.out.println(stra);

    }
}
