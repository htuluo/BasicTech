package basic.tech.memory;

/**
 * @description: VM args -Xss128k
 * @author: luolm
 * @createTime： 2019/7/1
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class JavaVMStackSOF {
    private int count=0;
    public void method(){
        System.out.println(++count);
        method();
    }

    public static void main(String[] args) {
        new JavaVMStackSOF().method();
    }
}
