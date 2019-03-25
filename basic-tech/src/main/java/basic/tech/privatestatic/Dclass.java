package basic.tech.privatestatic;

/**
 * @description:
 * @author: luolm
 * @createTime： 2018/11/16
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class Dclass {
    public static void main(String[] args) {
        System.out.println(Pclass.b);
        Pclass pclass = new Pclass();
        System.out.println(pclass.b);
    }
}
