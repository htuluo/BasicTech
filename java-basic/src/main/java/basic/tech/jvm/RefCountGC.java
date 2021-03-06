package basic.tech.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * @description: 循环引用的GC演示，强软弱虚引用的demo
 * @author: luolm
 * @createTime： 2019/10/30
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class RefCountGC {
    private char[] chars = null;
    private Object instance = null;

    public static void main(String[] args) {
        phantomReferenceTest();
    }

    /**
     * phantomReferenceTest，虚引用，get不到值，但回收之后进入referenceQueue队列
     */
    private static void phantomReferenceTest() {
        PhantomReference<Object> weakReference = null;
        try {
            Object ob1 = new Object();

            ReferenceQueue<Object> q = new ReferenceQueue<>();
            weakReference = new PhantomReference<Object>(ob1, q);
            System.out.println(ob1);
            System.out.println(weakReference.get());
            System.out.println(q.poll());
            ob1 = null;
            System.gc();
            Thread.sleep(500);
            System.out.println("==================");
            System.out.println(ob1);
            System.out.println(weakReference.get());
            System.out.println(q.poll());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            System.out.println("=====newKey\t" + weakReference.get());

        }
    }

    /**
     * referenceQueueTest，对象回收放进referenceQueue
     */
    private static void referenceQueueTest() {
        WeakReference<Object> weakReference = null;
        try {
            Object ob1 = new Object();

            ReferenceQueue<Object> q = new ReferenceQueue<>();
            weakReference = new WeakReference<Object>(ob1, q);
            System.out.println(ob1);
            System.out.println(weakReference.get());
            System.out.println(q.poll());
            ob1 = null;
            System.gc();
            System.out.println("==================");
            System.out.println(ob1);
            System.out.println(weakReference.get());
            System.out.println(q.poll());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            System.out.println("=====newKey\t" + weakReference.get());

        }
    }

    /**
     * softReference的使用，只要有GC，够不够的时候都清除（引用需要被置为null）
     * -Xms10m -Xmx10m -XX:+PrintGCDetails
     */
    private static void weakReferenceTest() {
        WeakReference<Object> softReference = null;
        try {
            Object ob1 = new Object();

            softReference = new WeakReference<Object>(ob1);
            System.out.println("=====ob1\t" + softReference.get());
            ob1 = null;
            byte[] key = new byte[3 * 1024 * 1024];
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("=====newKey\t" + softReference.get());

        }
    }

    /**
     * softReference的使用，当够的时候不清楚，堆不够的时候才清除（引用需要被置为null）
     * -Xms10m -Xmx10m -XX:+PrintGCDetails
     */
    private static void softReferencTest() {
        SoftReference<Object> softReference = null;
        try {
            Object newKey = new Object();

            softReference = new SoftReference<Object>(newKey);
            System.out.println("=====newKey\t" + softReference.get());
            newKey = null;
            byte[] key = new byte[3 * 1024 * 1024];
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("=====newKey\t" + softReference.get());

        }
    }

    /**
     * weakHashMap使用，当内存够时不清除，不够时清除所有key
     * -Xms10m -Xmx10m -XX:+PrintGCDetails
     */
    private static void weakHashMapTest() {
        WeakHashMap<String, Object> map = null;
        try {
            map = new WeakHashMap<>();
            String key = "key";
            String value = "value";
//            byte[] b = new byte[10 * 1024 * 1024];
            byte[] b = new byte[10 * 1024 * 1024];
            map.put("dd", b);
            map.put("aa", "ccc");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t=====" + map.get("aa") + "\t" + map.get(key));
            key = null;
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "\t=====" + map.get("aa"));
        }
    }


    /**
     * 循环引用的示例
     */
    private static void cycleReference() {

        //-XX:+PrintGCDetails配置打印详细GC日志
        RefCountGC refA = new RefCountGC();
        refA.chars = new char[2 * 1024 * 1024];
        RefCountGC refB = new RefCountGC();
        refA.instance = refB;
        refB.instance = refA;
        refA = null;
        refB = null;
        System.gc();
    }
}
