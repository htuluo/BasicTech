package basic.tech.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description: 演示线程报错的demo
 * @author: luolm
 * @createTime： 2019/11/15
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ThreadExceptionDemo {

    public static void main(String[] args) {
//        threadPoolDemo();

//        singleThreadDemo();
        singleCallableThreadDemo();

        System.out.println("*************");
    }


    private static void threadPoolDemo() {
        //        ThreadFactory threadFactory = new DefaultThreadFactory("poolName");
        ThreadFactory threadFactory = new MyThreadFactory(new MyUncaultExceptionHandler());
//        threadFactory.
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 3, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(12),
                threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());

        try {
            List<Future<?>> submits = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                //            Thread thread=new Thread(new MyRunable());
//                Future<?> submit = threadPoolExecutor.submit(new MyRunable());
                Future<?> submit = threadPoolExecutor.submit(new MyCallable());
                submits.add(submit);

            }
//            submits.forEach(x -> {
//                try {
//                    System.out.println(x.get());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//            });
//            for (int i = 0; i < 10; i++) {
//                System.out.println(submits.get(i).get());
//            }
            for (Future f : submits) {
//                while (!f.isDone()) {
//                }
                System.out.println("submit result:" + f.get());
            }
//            threadPoolExecutor.
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
//            TimeUnit.SECONDS.sleep(4);
            while (threadPoolExecutor.getActiveCount() > 0) {
            }
//            threadPoolExecutor.getRejectedExecutionHandler();
            threadPoolExecutor.shutdown();
            System.out.println(threadPoolExecutor.getTaskCount() + " isTerminated " + threadPoolExecutor.isTerminated());
            System.out.println(threadPoolExecutor.getTaskCount() + " isShutDown " + threadPoolExecutor.isShutdown());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单线程报错
     */
    private static void singleThreadDemo() {
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
    }


    /**
     * 单线程Callable示例
     */
    private static void singleCallableThreadDemo() {
        FutureTask<String> thread = new FutureTask<String>(new MyCallable());
        try {
            thread.run();
            //加不加try效果一样
            thread.get();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("singleCallableThreadDemo   Catch ========");
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

}

class MyThreadFactory implements ThreadFactory {
    private MyUncaultExceptionHandler uncaultExceptionHandler;

    public MyThreadFactory(MyUncaultExceptionHandler uncaultExceptionHandler) {
        this.uncaultExceptionHandler = uncaultExceptionHandler;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setUncaughtExceptionHandler(uncaultExceptionHandler);
        return thread;
    }
}

/**
 * runable
 */
class MyRunable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        throw new RuntimeException("dddd");
    }
}

class MyCallable implements Callable<String> {
    @Override
    public String call() {
        System.out.println(Thread.currentThread().getName() + " is calling");
        if (1 == 1) {
            throw new RuntimeException("dddd");

        }
        try {
            TimeUnit.SECONDS.sleep(1);
            return "OK";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }
}

class MyUncaultExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t.getName() + " uncaughtException catch " + e.getLocalizedMessage());
//        try {
//            TimeUnit.SECONDS.sleep(20);
//        } catch (InterruptedException e1) {
//            e1.printStackTrace();
//        }
        System.out.println(t.getName() + " isAlive:" + t.isAlive());
    }
}


