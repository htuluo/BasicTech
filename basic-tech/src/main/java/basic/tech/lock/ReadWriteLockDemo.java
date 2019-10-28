package basic.tech.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 读写锁的使用
 * @author: luolm
 * @createTime： 2019/10/28
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ReadWriteLockDemo {
    private Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t开始写入：" + key);
            Thread.sleep(new Random().nextInt(500));
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t写入完成：" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }


    }

    public Object get(String key) {
        lock.readLock().lock();
        try {
            Thread.sleep(new Random().nextInt(500));
            System.out.println(Thread.currentThread().getName() + "\t开始读取：" + key);
            return map.get(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.readLock().unlock();
        }

    }

    public static void main(String[] args) {
        ReadWriteLockDemo myclass = new ReadWriteLockDemo();
        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                myclass.put(key, key);

            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                Object o = myclass.get(key);
                System.out.println(Thread.currentThread().getName() + "\t读取完成：result-" + o);

            }, String.valueOf(i)).start();
        }
    }

}
