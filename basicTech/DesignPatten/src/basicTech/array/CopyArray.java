package basicTech.array;

/**
 * @description:
 * @author: luolm
 * @createTime： 2018/11/30
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class CopyArray {
    public static void main(String[] args) {

        char[] src=new char[]{'1','2','3'};
        char[] dest=new char[10];
        int destPosth=3;
        System.arraycopy(src,1,dest,destPosth,2);
        System.out.println(String.valueOf(dest));
    }
}
