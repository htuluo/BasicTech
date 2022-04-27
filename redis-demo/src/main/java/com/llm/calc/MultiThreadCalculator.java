package com.llm.calc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ： luoleiming
 * @date ：Created in 2022/4/26
 * @description： //TODO
 */
public class MultiThreadCalculator implements Calculator {
    private final int nThreads;
    private final ExecutorService pool;

    public MultiThreadCalculator(int nThreads, ExecutorService pool) {
        this.nThreads = nThreads;
        this.pool = Executors.newFixedThreadPool(nThreads);
    }

    private class SumTask implements Callable<Long> {
        private int[] nums;
        private int from;
        private int to;

        public SumTask(int[] nums, int from, int to) {
            this.nums = nums;
            this.from = from;
            this.to = to;
        }

        public Long call() throws Exception {
            long total = 0L;
            for (int i = from; i < to; i++) {
                total += i;
            }
            return total;
        }
    }

    @Override
    public long sum(int[] numbers) {

        int chunk = numbers.length / nThreads;
        int from, to;
        List<SumTask> tasks = new ArrayList<>();
        for (int i = 0; i < nThreads; i++) {
            if (i == nThreads) {
                from = (i - 1) * chunk;
                to = numbers.length;
            } else {
                from = (i - 1) * chunk;
                to = i * chunk;
            }
            tasks.add(new SumTask(numbers, from, to));

        }
        try {
            List<Future<Long>> futureTasks = pool.invokeAll(tasks);
            long total = 0L;
            for (Future<Long> future : futureTasks) {
                total += future.get();
            }
            return total;
        } catch (Exception e) {

        }

        return 0;
    }

    @Override
    public void shutdown() {

        pool.shutdown();
    }
}
