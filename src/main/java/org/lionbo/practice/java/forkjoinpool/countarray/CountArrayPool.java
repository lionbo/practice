package org.lionbo.practice.java.forkjoinpool.countarray;

import java.util.concurrent.ForkJoinPool;

public class CountArrayPool {

    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();
        long[] inputArray = new long[] { 1, 5, 8, 3, 10, 2, 7, 13 };
        long result = fjp.invoke(new CountArrayTask(inputArray));
        System.out.println(result);
    }

}
