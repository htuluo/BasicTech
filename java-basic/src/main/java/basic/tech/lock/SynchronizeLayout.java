package basic.tech.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @description: 偏向锁的对象展示
 * @author: luolm
 * @createTime： 2020/6/26
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SynchronizeLayout {
    class TestClass{
        private Object object;
        private int intA;
    }
    public static void main(String[] args) throws InterruptedException {
        testForCommonClassLayout();
    }

    /**
     * 普通类的内存结构
     * basic.tech.lock.SynchronizeLayout$TestClass object internals:
     *  OFFSET  SIZE                                TYPE DESCRIPTION                               VALUE
     *       0     4                                     (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     *       4     4                                     (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     *       8     4                                     (object header)                           43 c1 00 20 (01000011 11000001 00000000 00100000) (536920387)
     *      12     4                                 int TestClass.intA                            0
     *      16     4                    java.lang.Object TestClass.object                          null
     *      20     4   basic.tech.lock.SynchronizeLayout TestClass.this$0                          (object)
     * Instance size: 24 bytes
     *
     */
    public static void testForCommonClassLayout() {
        TestClass testClass=(new SynchronizeLayout()).new TestClass();
        System.out.println(ClassLayout.parseInstance(testClass).toPrintable());
    }

    public static void testForLayout() {
        //先sleep后，对象会加上匿名偏向锁
//        Thread.sleep(5000);
        Object o = new Object();
        System.out.println(Integer.toBinaryString(o.hashCode()));
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());

        }
    }
}
