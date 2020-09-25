package basic.tech.timer;

import java.util.*;
import java.util.concurrent.*;

/**
 * @description:
 * @author: luolm
 * @createTime： 2018/12/7
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class TimeStudy {
    public static void main(String[] args) {

//        time1();
//        time2();
//        time3();
        time4();
    }

    public static void time1() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("delay task");
            }
        }, 2000);
    }

    public static void time2() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("delay task,schedule time");
            }
        }, 2000, 500);
    }

    public static void time3() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("scheduleAtFixedRate delay task,schedule time");
            }
        }, 1000, 500);
    }

    /**
     * timer启动两个task，其中一个task报错，整个timer就会停掉
     */
    public static void time4() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 18);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("time4 delay task,schedule time");
            }
        }, date, 1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("time4 get");
                int i = 0;
                while (true) {
                    i++;
                    if (i > 5) {
                        throw new RuntimeException("new runtime");
                    }
                }
            }
        }, 3000);
    }


    public static void time5() {
        ScheduledExecutorService timer = new ScheduledExecutorService() {
            @Override
            public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
                return null;
            }

            @Override
            public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
                return null;
            }

            @Override
            public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
                return null;
            }

            @Override
            public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
                return null;
            }

            @Override
            public void shutdown() {

            }

            @Override
            public List<Runnable> shutdownNow() {
                return null;
            }

            @Override
            public boolean isShutdown() {
                return false;
            }

            @Override
            public boolean isTerminated() {
                return false;
            }

            @Override
            public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public <T> Future<T> submit(Callable<T> task) {
                return null;
            }

            @Override
            public <T> Future<T> submit(Runnable task, T result) {
                return null;
            }

            @Override
            public Future<?> submit(Runnable task) {
                return null;
            }

            @Override
            public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
                return null;
            }

            @Override
            public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
                return null;
            }

            @Override
            public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }

            @Override
            public void execute(Runnable command) {

            }
        };
    }

}
