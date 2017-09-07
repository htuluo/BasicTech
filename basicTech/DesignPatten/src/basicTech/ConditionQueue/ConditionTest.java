package basicTech.ConditionQueue;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dell-3020 on 2017/6/27.
 */
public class ConditionTest {
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    private String[] array = new String[10];
    private int count = 0;
    private int putIndex, takeIndex;

    public void put(String x) throws InterruptedException {
        lock.lock();
        try {
            while (count == array.length) {
                notFull.await();
            }
            array[putIndex] = x;
            if (++putIndex == array.length)
                putIndex = 0;
            ++count;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public String take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            String s = array[takeIndex];
            if (++takeIndex == array.length)
                takeIndex = 0;

            --count;
            notFull.signal();
            return s;
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        ConditionTest test = new ConditionTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (Integer i = 0; i < 10; i++) {

                        test.put(i + "22");
                        Thread.sleep(60);
                        if (i==6){
                            Thread.sleep(100);
                        }
                        System.out.println("put i=" + i.toString());
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    for (Integer i = 0; i < 20; i++) {

                        String s = test.take();
                        System.out.println("take i=" + i.toString() + ";take-" + s);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    System.exit(0);

    }
}
