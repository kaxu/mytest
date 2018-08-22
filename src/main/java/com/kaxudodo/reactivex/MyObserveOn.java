package com.kaxudodo.reactivex;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * Created by aaron on 2018/6/27.
 */
public class MyObserveOn {

    ExecutorService poolA = newFixedThreadPool(10, threadFactory("Sched-A-%d")); Scheduler schedulerA = Schedulers.from(poolA);

    ExecutorService poolB = newFixedThreadPool(10, threadFactory("Sched-B-%d")); Scheduler schedulerB = Schedulers.from(poolB);

    ExecutorService poolC = newFixedThreadPool(10, threadFactory("Sched-C-%d")); Scheduler schedulerC = Schedulers.from(poolC);

    private ThreadFactory threadFactory(String pattern) {
        return new ThreadFactoryBuilder()
                .setNameFormat(pattern)
                .build();
    }

    public Observable<String> simple() {
        return Observable.create(subscriber -> {
            log("Subscribed");
            subscriber.onNext("A");
            subscriber.onNext("B");
            subscriber.onCompleted();
        });
    }

    public void log(Object m){
        ReactiveUtil.log(m);
    }

    public void log(Object m,long start){
        ReactiveUtil.log(m,start);
    }

    public void test1(){
        log("Starting");
        final Observable<String> obs = simple();
        log("Created");
        obs .doOnNext(x -> log("Found 1: " + x))
                .observeOn(schedulerA)
                .doOnNext(x -> log("Found 2: " + x))
                .subscribe( x -> log("Got 1: " + x),
                        Throwable::printStackTrace, () -> log("Completed") );
        log("Exiting");
    }

    /**
     * multiple observeOn()
     */
    public void test2(){
        log("Starting");
        final Observable<String> obs = simple();
        log("Created");
        obs .doOnNext(x -> log("Found 1: " + x))
                .observeOn(schedulerB)
                .doOnNext(x -> log("Found 2: " + x))
                .observeOn(schedulerC)
                .doOnNext(x -> log("Found 3: " + x))
                .subscribeOn(schedulerA)
                .subscribe( x -> log("Got 1: " + x), Throwable::printStackTrace,
                        () -> log("Completed") );
        log("Exiting");
    }

    Observable<UUID> store(String s) {
        return Observable.create(subscriber -> {
            log("Storing " + s); //hard work
            subscriber.onNext(UUID.randomUUID());
            subscriber.onCompleted();
        });
    }

    /**
     * Here is a more advanced program that takes advantage of these two operators:
     */
    public void test3(){
        log("Starting");
        Observable<String> obs = Observable.create(
                subscriber -> {
                    log("Subscribed");
                    subscriber.onNext("A");
                    subscriber.onNext("B");
                    subscriber.onNext("C");
                    subscriber.onNext("D");
                    subscriber.onCompleted(); });
        log("Created");
        obs .subscribeOn(schedulerA)
                .flatMap(record -> store(record)
                        .subscribeOn(schedulerB))
                .observeOn(schedulerC)
                .subscribe( x -> log("Got: " + x),
                        Throwable::printStackTrace,
                        () -> log("Completed") );
        log("Exiting");
    }

    public static void main(String[] args) {
        MyObserveOn myObserveOn = new MyObserveOn();
        myObserveOn.test1();


    }


}
