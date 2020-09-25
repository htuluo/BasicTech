package basic.thread;

import java.util.concurrent.Callable;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/10/14
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class CallableSumCs implements Callable {
    private long[] arr;

    public CallableSumCs(long[] arr) {
        this.arr = arr;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int j = 0; j < arr.length; j++) {
            sum += arr[j];
        }
        return sum;
    }
}