package org.lionbo.practice.java.forkjoinpool.countfile;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolTest {

    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();

        fjp.invoke(new CountFileTask("/User"));
    }
}
