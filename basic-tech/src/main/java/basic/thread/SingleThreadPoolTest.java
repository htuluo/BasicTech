package basic.thread;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/10/15
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SingleThreadPoolTest {
    private static final int threads = 100;
    private CountDownLatch countDownLatch = new CountDownLatch(threads);

    /**
     * singleThreadPool execute
     *
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        System.out.println("---- begin ----");
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < threads; i++) {
            singleThreadExecutor.execute(() -> {
                printThreadInfo();
            });
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }


    /**
     * singleThreadPool submit
     *
     * @throws InterruptedException
     */
    @Test
    public void test2() throws InterruptedException {
        System.out.println("---- begin ----");
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < threads; i++) {
            singleThreadExecutor.submit(new Thread(() -> {
                printThreadInfo();
            }));
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }


    /**
     * singleThreadPool submit
     *
     * @throws InterruptedException
     */
    @Test
    public void test3() throws InterruptedException {
        CopyOnWriteArrayList<Integer> LIST_CACHE = new CopyOnWriteArrayList<>();
        LIST_CACHE.add(100);
        LIST_CACHE.add(14);
        LIST_CACHE.add(8);
        LIST_CACHE.add(7);
        LIST_CACHE.add(500);
        List list = (ArrayList) LIST_CACHE.parallelStream().filter(item ->
                Integer.compare((int) item, 10) < 0
        ).collect(Collectors.toList());
        System.out.println(list);
    }


    /**
     * 打印线程信息
     */
    private void printThreadInfo() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(50);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            countDownLatch.countDown();
            System.out.println("countDownLatch==" + countDownLatch.toString());
        }
    }
}