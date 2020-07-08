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
    public static void main(String[] args) throws InterruptedException {
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
