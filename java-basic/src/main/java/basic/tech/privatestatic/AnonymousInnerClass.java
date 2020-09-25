package basic.tech.privatestatic;

/**
 * @description: 匿名内部类的使用Demo
 * @author: luolm
 * @createTime： 2019/11/15
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class AnonymousInnerClass {
    public static void main(String[] args) {
        Outer.method().show();
    }
}

interface inter {
    void show();
}

class Outer {
    public static inter method() {
        inter inter = new inter() {

            @Override
            public void show() {
                System.out.println("HelloWorld");

            }
        };
        return inter;
    }

}
