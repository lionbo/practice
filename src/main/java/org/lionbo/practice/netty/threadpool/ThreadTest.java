package org.lionbo.practice.netty.threadpool;

import java.util.concurrent.TimeUnit;

public class ThreadTest {

    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (!stopRequested)
                    i++;
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }

}
