package org.lionbo.practice.join;

import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang3.RandomUtils;

public class ThreadJoinTest {

    public static void main(String[] args) {
        final int[] sum = new int[5];
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            final int index = i;
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(RandomUtils.nextLong(1000, 5000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(index);
                    sum[index] = index + 1;
                    latch.countDown();
                }
            });
            t.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(sum[i]);
        }

    }

}
