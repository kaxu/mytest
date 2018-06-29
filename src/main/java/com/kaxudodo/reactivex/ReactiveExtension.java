package com.kaxudodo.reactivex;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by aaron on 2018/6/7.
 */
public class ReactiveExtension {

    public static void log(Object msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }

    static <T> Observable<T> delayed(T x) {
        return Observable.create(subscriber -> {
            Runnable r = () -> {
                sleep(3, SECONDS);
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(x);
                    subscriber.onCompleted();
                }
            };
            final Thread thread = new Thread(r);
            thread.start();
            subscriber.add(Subscriptions.create(thread::interrupt));

        });

    }

    static void sleep(int timeout, TimeUnit unit) {
        try {

            unit.sleep(timeout);
        } catch (InterruptedException ignored) {

//intentionally ignored
        }
    }

    public static void main(String[] args) {
//        log("Before");
//        Observable.range(5, 3) .subscribe(i -> {
//            log(i); });
//        log("After");


//        Observable<Integer> ints = Observable .create(new Observable.OnSubscribe<Integer>() {
//            @Override public void call(Subscriber<? super Integer> subscriber) {
//                log("Create");
//                subscriber.onNext(5);
//                subscriber.onNext(6);
//                subscriber.onNext(7);
//                subscriber.onCompleted();
//                log("Completed"); }
//
//        }).cache();
//        log("Starting"); ints.subscribe(i -> log("Element: " + i)); log("Exit");
//        log("Starting"); ints.subscribe(i -> log("Element: " + i)); log("Exit");


//        Observable<BigInteger> naturalNumbers = Observable.create(subscriber -> {
//            Runnable r = () -> {
//                BigInteger i = ZERO;
//                while (!subscriber.isUnsubscribed()) {
//                    subscriber.onNext(i);
//                    i = i.add(ONE);
//                }
//            };
//            new Thread(r).start();
//        });
//        log("Starting");
//        Subscription subscription = naturalNumbers.subscribe(x -> log(x));
//        //after some time...
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        subscription.unsubscribe();
//        log("Ending");


        Observable<BigInteger> delayedObservable = delayed(new BigInteger("3"));
        Subscription subscription = delayedObservable.subscribe(x -> log(x));
        subscription.unsubscribe();


    }
}
