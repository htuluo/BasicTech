package basic.tech.concurrent;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/11/1
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

        //============ABA的引起==========
        new Thread(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(MessageFormat.format("{0}\t{1}\t", name, atomicReference.compareAndSet(100, 101)));
            System.out.println(MessageFormat.format("{0}\t{1}\t", name, atomicReference.compareAndSet(101, 100)));

            System.out.println(atomicReference.get());
        }, "AA").start();
        new Thread(() -> {
            String name = Thread.currentThread().getName();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(MessageFormat.format("{0}\t{1}\t", name, atomicReference.compareAndSet(100, 2011)));
            System.out.println(atomicReference.get());

        }, "BB").start();
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //============ABA的解决==========,Integer的==比较，需要是同一个地址的值！！！
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(10, 1);

        new Thread(() -> {
            String name = Thread.currentThread().getName();
            int stamp = atomicStampedReference.getStamp();
            System.out.println(MessageFormat.format("{0}\tstamp:{1}", name, stamp));
            boolean b = atomicStampedReference.compareAndSet(10, 128, stamp, ++stamp);
            System.out.println(MessageFormat.format("{0}\t{1}\t", name, b));
            System.out.println(MessageFormat.format("{0}\t 第二次stamp:{1}", name, atomicStampedReference.getStamp()));
            boolean b1 = atomicStampedReference.compareAndSet(128, 10, stamp, ++stamp);
            System.out.println(MessageFormat.format("{0}\t{1}\t", name, b1));
            System.out.println(MessageFormat.format("{0}\t 第三次stamp:{1}", name, atomicStampedReference.getStamp()));

            System.out.println(MessageFormat.format("{0}\t{1}", name, atomicStampedReference.getReference()));
        }, "CC").start();
        new Thread(() -> {
            String name = Thread.currentThread().getName();
            int stamp = atomicStampedReference.getStamp();
            System.out.println(MessageFormat.format("{0}\tstamp:{1}", name, stamp));
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(MessageFormat.format("{0}\t{1}\t", name,
                    atomicStampedReference.compareAndSet(10, 12, stamp, stamp + 1)));
            System.out.println(MessageFormat.format("{0}\t{1}", name, atomicStampedReference.getReference()));

        }, "DD").start();

    }
}
