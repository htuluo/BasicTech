package basic.tech.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示堆溢出，一个线程OOM之后，另一个线程照常运行
 * 需要debugArguments设置：-Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
 *
 * @author leimingluo
 */
public class MemoryTest {
    static class OOMObject {
    }

    public static void main(String[] args) {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<OOMObject> list = new ArrayList<>();
                while (true) {
                    list.add(new OOMObject());
//                    System.out.println("Thread " + Thread.currentThread().getName() + " is working");
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
                }
            }
        });
        thread.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread " + Thread.currentThread().getName() + " is running");
                }
            }
        });
        thread2.start();
    }

}
