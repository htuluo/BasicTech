package basic.tech.atomic;

import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/10/27
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class AtomicIntegerDemo2 {
    private static AtomicInteger atomicInteger = new AtomicInteger(4);
    private static AtomicReference<User> atomicReference = new AtomicReference<>();

    private static class User {
        public String name;
        public int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return MessageFormat.format("'{'name:{0},age:{1}'}", name, age);
        }
    }

    public static void main(String[] args) {
        atomicReferenceTest();
//        atomicReference.set(user1);

    }

    /**
     * 原子引用实例Test
     */
    private static void atomicReferenceTest() {
        User user1 = new User("z1", 1);
        User user2 = new User("lisi1", 2);

        System.out.println(atomicReference.getAndSet(user1));
        System.out.println(atomicReference.compareAndSet(user1, user2) + "\t current value " + atomicReference.get());
        System.out.println(atomicReference.compareAndSet(user1, user2) + "\t current value " + atomicReference.get());
    }

    /**
     * atmoicinteger测试
     */
    private static void testAtomicInteger() {
        new Thread(() -> {
            System.out.println(atomicInteger.compareAndSet(4, 5) + "\t current value=" + atomicInteger.get());
            System.out.println(atomicInteger.compareAndSet(4, 5) + "\t current value=" + atomicInteger.get());

            System.out.println("aaa");
        }, "thread").start();
    }
}
