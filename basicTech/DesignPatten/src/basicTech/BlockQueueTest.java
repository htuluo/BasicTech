package basicTech;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dell-3020 on 2017/6/29.
 */
public class BlockQueueTest {
    private List<String> queue=new LinkedList<>();
    private  int limit=10;

    public synchronized  void    enQueue(String s) throws InterruptedException {
        while (this.queue.size()==this.limit){
            wait();
        }
        if (this.queue.size()==0){
            notifyAll();
        }
        queue.add(s);

    }
    public synchronized  String    deQueue() throws InterruptedException {
        while (this.queue.size()==0){
            wait();
        }
        if (this.queue.size()==this.limit){
            notifyAll();
        }
        String s1 = queue.get(0);
        queue.remove(0);
        return s1;

    }

    public static void main(String[] args) {
        BlockQueueTest test=new BlockQueueTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (Integer i = 0; i < 10; i++) {

                        test.enQueue(i + "22");
//                        Thread.sleep(60);
                        if (i==6){
                            Thread.sleep(1000);
                        }
                        System.out.println("put i=" + i.toString());
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    for (Integer i = 0; i < 20; i++) {

                        String s = test.deQueue();
                        System.out.println("take i=" + i.toString() + ";take-" + s);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
