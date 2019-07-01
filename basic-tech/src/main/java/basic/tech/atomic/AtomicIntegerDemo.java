package basic.tech.atomic;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/6/28
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class AtomicIntegerDemo {
    public static AtomicInteger count =new AtomicInteger( 0);
    public static int num = 20;

    public static void main(String[] args) {
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
