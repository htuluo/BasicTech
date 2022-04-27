package com.llm.benchmark;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author ： luoleiming
 * @date ：Created in 2022/4/26
 * @description： //TODO
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class FirstBenchmark {
    @Benchmark
    public int sleepAWhile() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(FirstBenchmark.class.getSimpleName())
                //启动几个进程测试
                .forks(2)
                //预热次数
                .warmupIterations(5)
                //实际迭代次数
                .measurementIterations(5)
                .build();
        new Runner(options).run();
    }
}
