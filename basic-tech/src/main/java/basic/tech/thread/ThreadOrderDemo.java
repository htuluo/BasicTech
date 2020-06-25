package basic.tech.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/6/25
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ThreadOrderDemo {
    class MyThread extends Thread {
        private Semaphore semaphore = null;

        public void setSemaphore(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        public MyThread() {
        }

        @Override
        public void run() {
            try {
                int i = 0;
                while (true) {
                    i++;
                    semaphore.acquire();
//                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + "  is running ,i==" + i);

//                    }
                    TimeUnit.MILLISECONDS.sleep(1000);
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

    class MyThread2 extends Thread {
        private Semaphore semaphore = null;

        public void setSemaphore(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        public MyThread2() {
        }

        @Override
        public void run() {
            try {
                int i = 0;
                while (true) {
                    i++;
                    semaphore.acquire();
//                    if (i % 2 == 1) {
                    System.out.println(Thread.currentThread().getName() + " is running ,i==" + i);

//                    }
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1, true);
        ThreadOrderDemo threadOrderDemo = new ThreadOrderDemo();
        MyThread myThread = threadOrderDemo.new MyThread();
        myThread.setSemaphore(semaphore);
        myThread.setName("my-1");
        MyThread2 myThread2 = threadOrderDemo.new MyThread2();
        myThread2.setSemaphore(semaphore);
        myThread2.setName("my-2");
        myThread.start();
        myThread2.start();

    }
}
