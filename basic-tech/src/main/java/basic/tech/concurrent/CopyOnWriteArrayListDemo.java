package basic.tech.concurrent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @description: CopyOnWriteArrayList的线程安全性验证
 * @author: luolm
 * @createTime： 2020/7/2
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class CopyOnWriteArrayListDemo {
    private static List<Integer> copyOnWriteArrayList=new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int i1 = 0; i1 < 100000; i1++) {
                    copyOnWriteArrayList.add(i1);
                }
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(copyOnWriteArrayList.size());
    }
}
