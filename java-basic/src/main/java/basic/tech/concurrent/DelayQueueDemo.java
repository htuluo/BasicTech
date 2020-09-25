package basic.tech.concurrent;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @description: DelayQueue的使用
 * @author: luolm
 * @createTime： 2019/11/9
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class DelayQueueDemo {
    public static void main(String[] args) throws InterruptedException {

        DelayQueue<ThreadNode> queue = new DelayQueue<ThreadNode>();
        queue.put(new ThreadNode(1, 10, TimeUnit.SECONDS));
        queue.put(new ThreadNode(2, 3, TimeUnit.SECONDS));
        queue.put(new ThreadNode(3, 5, TimeUnit.SECONDS));
        queue.put(new ThreadNode(4, 7, TimeUnit.SECONDS));
        for (; ; ) {
            System.out.println(queue.poll(1,TimeUnit.SECONDS));
            if (queue.size()==0){
                break;
            }

        }

    }
}

class ThreadNode implements Delayed {
    private int threadId;
    private long delaytime;

    public ThreadNode(int threadId, long delaytime, TimeUnit unit) {
        this.threadId = threadId;
        this.delaytime = System.currentTimeMillis() + ((delaytime > 0) ? unit.toMillis(delaytime) : 0);
    }

    @Override
    public String toString() {
        return "ThreadNode{" +
                "threadId=" + threadId +
                ", delaytime=" + delaytime +
                '}';
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return delaytime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        ThreadNode o1 = (ThreadNode) o;
        long l = o1.delaytime - this.delaytime;
        if (l >= 0) {
            return -1;
        }
        return 1;
    }
}
