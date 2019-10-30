package basic.tech.classinit;

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
        ReferenceTransferDemo referenceTransferDemo = new ReferenceTransferDemo();
        System.out.println(referenceTransferDemo.str + " " + String.valueOf(referenceTransferDemo.chars) + " " + referenceTransferDemo.anInt);
        referenceTransferDemo.change(referenceTransferDemo.str, referenceTransferDemo.chars, referenceTransferDemo.anInt);
        System.out.println(referenceTransferDemo.str + " " + String.valueOf(referenceTransferDemo.chars + " " + referenceTransferDemo.anInt));
        String stra="bbbs";
        referenceTransferDemo.change(stra);
        System.out.println(stra);

    }
}
