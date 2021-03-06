package com.currency.demo.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: LQ
 * @Date: 2018/10/28 21:09
 * @Description: CountDownLatch
 */
public class CountDownLatchExp2 {

    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService es = Executors.newSingleThreadExecutor();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            es.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(600, TimeUnit.MILLISECONDS);
        es.shutdown();
        System.out.println(" finish ");
    }

    private static void test(int num) throws InterruptedException {
        Thread.sleep(350);
        System.out.println(" threadNum is  " + num);
        Thread.sleep(150);
    }

}
