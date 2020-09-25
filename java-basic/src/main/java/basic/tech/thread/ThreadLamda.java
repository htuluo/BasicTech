package basic.tech.thread;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLamda {
    public static void main(String[] args) throws InterruptedException {
        String param = "dddddddddddddd";
        Integer ab = 220;
        TimeUnit.SECONDS.sleep(30);
        Thread thread = new Thread(() -> {
            System.out.println("ddd" + param + ab);
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            String param="ddd";
        },"aaa");
        thread.start();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //ab+=1;不能对变量进行更改，需要final的变量
                String param = "9999999999999";
                System.out.println(param + " " + ab);
                try {
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"bbb");

        thread1.start();

    }

    @Test
    public void test(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 300L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10));
        for (int i=0;i<18;i++){
            threadPoolExecutor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " is running");
                try {
                    int timeout = new Random().nextInt(10);
                    System.out.println("sleeping "+timeout);
                    TimeUnit.MILLISECONDS.sleep(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
