package basic.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 多线程交替打印
 * @author: luolm
 * @createTime： 2019/11/8
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ConditionDemo3 {
    private  int i;

    public ConditionDemo3(int i) {
        this.i = i;
    }

    private ReentrantLock lock = new ReentrantLock();
    private Condition oddCondition = lock.newCondition();
    private Condition evenCondition = lock.newCondition();

    public void printOddNum() throws InterruptedException {
        lock.lock();
        try {
            if (i >= 0 && i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
                i--;
                oddCondition.signal();
                evenCondition.await();
            } else {
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printEvenNum() throws InterruptedException {
        lock.lock();
        try {
            if (i >= 0 && i % 2 == 1) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
                i--;
                evenCondition.signal();
                oddCondition.await();
            } else {
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        ConditionDemo3 conditionDemo3 = new ConditionDemo3(10);

        new Thread(() -> {
            try {
                while (conditionDemo3.i >= 0) {
                    conditionDemo3.printEvenNum();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AA").start();
        new Thread(() -> {
            try {
                while (conditionDemo3.i >= 0) {
                    conditionDemo3.printOddNum();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
}
