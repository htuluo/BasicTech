package basic.thread;


import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * Created by dell-3020 on 2017/9/27.
 */
public class joinTest {
    static volatile Integer count = 0;
    static {
        System.out.println("Class init");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread threada = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("1");
            }
        });

        Thread threadb = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2");
            }
        });
        threada.start();
        threada.join();
        System.out.println("main a join finished");
        threadb.start();
        System.out.println("main start");
        threadb.join();
        System.out.println("main finished");
//        while (true) {
//            if (count % 2 == 0) {
//                if (threada.isAlive()) {
//                    threada.wait();
//                }
//                threadb.notify();
//            } else {
//                threada.notify();
//                if (threadb.isAlive()) {
//
//                    threadb.wait();
//                }
//            }
//        }


    }

    @Test
    public void test(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 30l, TimeUnit.SECONDS, new ArrayBlockingQueue<>(30), new ThreadPoolExecutor.AbortPolicy());
        for(int i=0;i<40;i++) {
            int finalI = i;
            threadPoolExecutor.submit(() -> {
               System.out.println("i===="+ finalI+"  "+ java.lang.Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("aaaaaa");
    }
}
