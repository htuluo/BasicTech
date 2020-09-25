package basic.tech.atomic;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/6/28
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class AtomicIntegerDemo {
    public static AtomicInteger count = new AtomicInteger(0);
    public static int num = 20;
    public static ExecutorService executor = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {
        threadPoolExecute();
        threadArrayExecute();
    }


    private static void  threadPoolExecute2(){
//        new Threadfactorybuilder
//        ThreadFactory
//        new ThreadPoolExecutor(5,20,5f, TimeUnit.MINUTES,new LinkedBlockingQueue<>(),)
//        new
    }

    /**
     * 线程池方式
     */
    private static void threadPoolExecute() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 1000; j++) {
                    count.addAndGet(1);
                }
            }
        };
        for (int i = 0; i < 30; i++) {

            executor.execute(runnable);

        }
        executor.shutdown();
        while (!executor.isTerminated()){
            Thread.yield();
        }
        System.out.println("count-"+count);
    }

    /**
     * 线程数组方式
     */
    private static void threadArrayExecute() {
        Thread[] threads = new Thread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        count.addAndGet(1);
                    }
                }
            });
            threads[i].start();
        }

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo info : threadInfos) {
            System.out.println("[" + info.getThreadId() + "]" + info.getThreadName());
        }
        System.out.println("活动线程数：" + Thread.activeCount());

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(count);
    }

}
