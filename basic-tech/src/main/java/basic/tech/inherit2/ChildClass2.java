package basic.tech.inherit2;

import basic.tech.inherit.ParentClass;

/**
 * @description: 演示不同package的继承时，不加访问修饰符的属性不能被子类继承
 * @author: luolm
 * @createTime： 2020/7/6
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ChildClass2 extends ParentClass {
    public static void main(String[] args) {
        ChildClass2 childClass2=new ChildClass2();
        childClass2.parentProtectStr="bbb";
    }
}
