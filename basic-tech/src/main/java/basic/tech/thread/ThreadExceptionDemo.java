package basic.tech.thread;

import java.util.concurrent.TimeUnit;

/**
 * @description: 演示线程报错的demo
 * @author: luolm
 * @createTime： 2019/11/15
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ThreadExceptionDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunable(), "AA");
        thread.setUncaughtExceptionHandler(new MyUncaultExceptionHandler());
        try {
            //加不加try效果一样
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Catch ========");
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println(thread.getName() + " isAlive:" + thread.isAlive());
        System.out.println("===========");
    }

}

class MyRunable implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException("dddd");
    }
}

class MyUncaultExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("uncaughtException catch " + e.getLocalizedMessage());
//        try {
//            TimeUnit.SECONDS.sleep(20);
//        } catch (InterruptedException e1) {
//            e1.printStackTrace();
//        }
        System.out.println(t.getName() + " isAlive:" + t.isAlive());
    }
}
