package com.llm.calc;

/**
 * @author ： luoleiming
 * @date ：Created in 2022/4/26
 * @description： //TODO
 */
public class SingleThreadCalculator implements Calculator {
    @Override
    public long sum(int[] numbers) {
        long total = 0L;
        for (int i : numbers) {
            total += i;
        }
        return 0;
    }

    @Override
    public void shutdown() {

    }
}
