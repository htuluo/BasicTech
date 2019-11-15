package basic.tech.thread;

/**
 * @description: 演示线程报错的demo
 * @author: luolm
 * @createTime： 2019/11/15
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ThreadExceptionDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunable());
        thread.setUncaughtExceptionHandler(new MyUncaultExceptionHandler());
        try {
            //加不加try效果一样
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Catch ========");
        }
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
        System.out.println("uncaughtException catch"+e.getLocalizedMessage());
    }
}
