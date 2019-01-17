package org.gnuhpc.bigdata.algorithm.recursion.fibo;

import org.junit.Test;

public class FiboAddAccuRecursive implements Fibo {

    @Override
    public long fib(long index) {
        //这里的第一个数字指的是还剩几个数，而b是结果
        return fib(index, 1, 0);
    }

    private long fib(long left, long nextVal, long accu) {
        return left == 0 ? accu : fib(left - 1, nextVal + accu, nextVal);
    }

    @Test
    public void test(){
        fib(4);
    }

}
