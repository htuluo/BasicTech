package basic.thread;

import sun.swing.ImageIconUIResource;

import java.util.Calendar;
import java.util.concurrent.*;
import java.util.stream.LongStream;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/10/14
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class MultiThreadCalSum implements CalSum {
    private ExecutorService executorService;

    private int pallelThreadCount;

    public MultiThreadCalSum() {
        pallelThreadCount = Runtime.getRuntime().availableProcessors();
        executorService = Executors.newFixedThreadPool(pallelThreadCount);
    }

    @Override
    public long sumUp(long[] arr) {
        int length = arr.length;
        if (length == 0) {
            return 0;
        }
        int groupCount = length / pallelThreadCount + 1;

        long sum = 0;
        for (int i = 0; i < pallelThreadCount; i++) {
            long[] subArray = new long[groupCount];
            int start = i * groupCount;
            if ((i + 1) * groupCount >= length) {
                groupCount = length - i * groupCount;
            }
            System.arraycopy(arr, start, subArray, 0, groupCount);
            Callable callable = new CallableSumCs(subArray);

            Future submit = executorService.submit(callable);
            try {
                int o = (int) submit.get();
                sum += o;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return sum;
    }


    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 10).toArray();
        long l = new MultiThreadCalSum().sumUp(numbers);
        System.out.println("result=" + String.valueOf(l));
    }

}
