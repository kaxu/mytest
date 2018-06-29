package com.kaxudodo.reactivex;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.concurrent.*;

import static com.kaxudodo.reactivex.ReactiveUtil.log;

/**
 * Created by aaron on 2018/6/21.
 */
public class MyScheduler {

    // 这段代码是为了Schedulers.from(Executor executor)
    public void test1(){
        ThreadFactory threadFactory = new ThreadFactoryBuilder() .setNameFormat("MyPool-%d") .build();
        Executor executor = new ThreadPoolExecutor( 10, //corePoolSize
            10, //maximumPoolSize
            0L, TimeUnit.MILLISECONDS, //keepAliveTime, unit
            new LinkedBlockingQueue<>(1000), //workQueue
            threadFactory
         );
        Scheduler scheduler = Schedulers.from(executor);
    }

    public void testImmediateAndTrampoline(){
        long start = System.currentTimeMillis();
        Scheduler scheduler = Schedulers.immediate();
//        Scheduler scheduler = Schedulers.trampoline();
        Scheduler.Worker worker = scheduler.createWorker();
        log("Main start",start);
        worker.schedule(() -> {
            log(" Outer start",start);
            sleepOneSecond();
            worker.schedule(() -> {
                log(" Middle start",start);
                sleepOneSecond();
                worker.schedule(() -> {
                    log(" Inner start",start);
                    sleepOneSecond();
                    log(" Inner end",start);
                });
                log(" Middle end",start);
            });
            log(" Outer end",start);
        });
        log("Main end",start);
        worker.unsubscribe();
    }

    public static void sleepOneSecond(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyScheduler myScheduler = new MyScheduler();
        myScheduler.testImmediateAndTrampoline();
    }
}
