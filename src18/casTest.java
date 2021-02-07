package com.rule;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author XinLiu
 * @version 1.0
 */
public class casTest {
    private static final Object lock = new Object();
    //private static int balance = 0;
    private static AtomicInteger balance = new AtomicInteger(0);

    public static void main(String[] args) {
        int count = 10000;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(32, 32, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        CountDownLatch downLatch = new CountDownLatch(count);
        try{
            for (int i = 0; i < count; i++) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        //锁
                        //syncAdd(downLatch);
                        //CAS
                        //casAdd(downLatch);
                        //FAA
                        //faaAdd(balance);
                    }
                });
            }
            downLatch.await();
            System.out.println(balance);
        } catch (InterruptedException e) {
            executor.shutdown();
        } finally {
            executor.shutdown();
        }
    }

    private static void casAdd(CountDownLatch downLatch) {
        while (!balance.compareAndSet(balance.get(),balance.get()+1)){
            Thread.yield();
        }
        downLatch.countDown();
    }

    private static void faaAdd(CountDownLatch downLatch) {
        balance.incrementAndGet();
        downLatch.countDown();
    }


    /*private static void syncAdd(CountDownLatch downLatch) {
        synchronized (lock) {
            balance += 1;
        }
        downLatch.countDown();
    }*/
}
