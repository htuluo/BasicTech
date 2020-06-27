package basic.classloader;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/6/27
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SimpleDemo {
    public static String a = "aa";
    public static final String b = "bb";

    static {
        System.out.println("SimpleDemo is initialized");
    }

    static String say() {
        return "say";
    }
}
