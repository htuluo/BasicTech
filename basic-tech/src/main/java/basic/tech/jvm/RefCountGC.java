package basic.tech.jvm;

/**
 * @description: 循环引用的GC演示
 * @author: luolm
 * @createTime： 2019/10/30
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class RefCountGC {
    private char[] chars = new char[2 * 1024 * 1024];
    private Object instance = null;

    public static void main(String[] args) {
        //-XX:+PrintGCDetails配置打印详细GC日志
        RefCountGC refA = new RefCountGC();
        RefCountGC refB = new RefCountGC();
        refA.instance = refB;
        refB.instance = refA;
        refA = null;
        refB = null;
        System.gc();
    }
}
