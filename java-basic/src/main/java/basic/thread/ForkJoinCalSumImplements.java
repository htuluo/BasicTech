package basic.thread;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/10/15
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ForkJoinCalSumImplements implements CalSum {
    private ForkJoinPool pool;

    public ForkJoinCalSumImplements() {
        this.pool = new ForkJoinPool();
    }

    @Override
    public long sumUp(long[] arr) {
        return pool.invoke(new SumTask(arr, 0, arr.length - 1));
    }

    private static class SumTask extends RecursiveTask<Long> {
        private long[] numbers;
        private int from;
        private int to;

        public SumTask(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }


        /**
         * The main computation performed by this task.
         *
         * @return the result of the computation
         */
        @Override
        protected Long compute() {
            if (to - from < 6) {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
            } else {
                int middle = (to + from) / 2;
                SumTask sumTaskLeft = new SumTask(numbers, from, middle);
                SumTask sumTaskRight = new SumTask(numbers, middle + 1, to);
                sumTaskLeft.fork();
                sumTaskRight.fork();
                return sumTaskLeft.join() + sumTaskRight.join();
            }
        }
    }

    public static void main(String[] args) {

        long[] longs = LongStream.rangeClosed(1, 100002).toArray();
        long l = new ForkJoinCalSumImplements().sumUp(longs);
        System.out.println(l);
    }

}
