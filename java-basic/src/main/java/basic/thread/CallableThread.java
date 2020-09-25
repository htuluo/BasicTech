package basic.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 有返回值的线程
 */
@SuppressWarnings("unchecked")
public class CallableThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("----程序开始运行----");
        Date date1 = new Date();

        int taskSize = 40;
        // 创建一个线程池
//		ExecutorService pool = Executors.newFixedThreadPool(taskSize);

        //下面类在com.google.guava包里
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("call-thread-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(2, 5, 7L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(13),
                threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Callable c = new MyCallable(i + "个任务");
            FutureTask<String> futureTask = new FutureTask<String>(c);
            // 执行任务并获取Future对象
            Future f = pool.submit(futureTask);
            list.add(futureTask);
        }
        // 获取所有并发任务的运行结果
        for (Future f : list) {
            // 从Future对象上获取任务的返回值，并输出到控制台
//            while (!f.isDone()) {
//            }
            System.out.println(MessageFormat.format(">>>执行状态isDone-{0},isCancelled-{1},>>>执行结果-{2}", f.isDone(), f.isCancelled(), f.get().toString()));
        }
        // 关闭线程池
        pool.shutdown();

        Date date2 = new Date();
        System.out.println("----主程序结束运行----，程序运行时间【" + (date2.getTime() - date1.getTime()) + "毫秒】");
    }
}

class MyCallable implements Callable<Object> {
    private String taskNum;

    MyCallable(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t>>>" + taskNum + "任务启动");
        Date dateTmp1 = new Date();
        Thread.sleep(1000);
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println(Thread.currentThread().getName() + "\t>>>" + taskNum + "任务终止");
        return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
    }
}