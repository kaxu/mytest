package com.kaxudodo.reactivex;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.tuple.Pair;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import static com.kaxudodo.reactivex.ReactiveUtil.log;
import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * Created by aaron on 2018/6/22.
 */
public class MySubscriberOn {

    ExecutorService poolA = newFixedThreadPool(10, threadFactory("Sched-A-%d")); Scheduler schedulerA = Schedulers.from(poolA);

    ExecutorService poolB = newFixedThreadPool(10, threadFactory("Sched-B-%d")); Scheduler schedulerB = Schedulers.from(poolB);

    ExecutorService poolC = newFixedThreadPool(10, threadFactory("Sched-C-%d")); Scheduler schedulerC = Schedulers.from(poolC);

    public Observable<String> simple() {
        return Observable.create(subscriber -> {
            log("Subscribed");
            subscriber.onNext("A");
            subscriber.onNext("B");
            subscriber.onCompleted();
        });
    }

    public void testSubscribe(){
        log("Starting");
        final Observable<String> obs = simple();
        log("Created");
        final Observable<String> obs2 = obs
                .map(x -> x)
                .filter(x -> true);
        log("Transformed");
        obs2.subscribe(
                x -> log("Got " + x),
                Throwable::printStackTrace,
                () -> log("Completed")
        );
        log("Exiting");
    }

    private ThreadFactory threadFactory(String pattern) {
        return new ThreadFactoryBuilder()
                .setNameFormat(pattern)
                .build();
    }

    public void testSubscribeOn(){
        ExecutorService poolA = newFixedThreadPool(10, threadFactory("Sched-A-%d"));
        Scheduler schedulerA = Schedulers.from(poolA);
        long start = System.currentTimeMillis();
        log("Starting",start);
        final Observable<String> obs = simple();
        log("Created",start);
        obs.subscribeOn(schedulerA)
//        obs.subscribeOn(Schedulers.io())
//                .delay(1, TimeUnit.SECONDS)
                .subscribe(
                        x -> log("Got " + x,start),
                        Throwable::printStackTrace,
                        () -> log("Completed",start)
                );
        log("Exiting",start);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void log(Object m){
        ReactiveUtil.log(m);
    }

    public void log(Object m,long start){
        ReactiveUtil.log(m,start);
    }

    public void testSubscribeOn2(){
        log("Starting");
        final Observable<String> obs = simple();
        log("Created");
        obs
                .doOnNext(this::log)
                .map(x -> x + '1')
                .doOnNext(this::log)
                .map(x -> x + '2')
                .doOnNext(this::log)
                .subscribeOn(schedulerA)
                .subscribe( x -> log("Got " + x), Throwable::printStackTrace, () -> log("Completed") );
        log("Exiting");
    }


    /**
     * true concurrency
     */
    public void testSubscribeOnGroceries(){
        RxGroceries rxGroceries = new RxGroceries();
        Observable<BigDecimal> totalPrice = Observable
                .just("bread", "butter", "milk", "tomato", "cheese")
                .subscribeOn(schedulerA)//BROKEN!!!
                .map(prod -> rxGroceries.doPurchase(prod, 1))
                .reduce(BigDecimal::add)
                .single();
        totalPrice.subscribe(this::log);
    }

    public void testSubscribeOnGroceriesConcurrently(){
        RxGroceries rxGroceries = new RxGroceries();
        Observable<BigDecimal> totalPrice = Observable .just("bread", "butter", "milk", "tomato", "cheese")
                .flatMap(prod -> rxGroceries .purchase(prod, 1)
                        .subscribeOn(schedulerA)) .reduce(BigDecimal::add)
                .single();
        totalPrice.subscribe(this::log);
    }

    public void testSubscribeOnGroceriesConcurrently2(){
        RxGroceries rxGroceries = new RxGroceries();
        Observable<BigDecimal> totalPrice = Observable .just("bread", "butter", "egg", "milk", "tomato",
                "cheese", "tomato", "egg", "egg")
                .groupBy(prod -> prod)
                .flatMap(grouped -> grouped .count()
                        .map(quantity -> { String productName = grouped.getKey();
                            return Pair.of(productName, quantity);
                        }))
                .flatMap(order -> rxGroceries.purchase(order.getKey(), order.getValue())
                        .subscribeOn(schedulerA))
                .reduce(BigDecimal::add)
                .single();
        totalPrice.subscribe(this::log);
    }

    public static void main(String[] args) {
        MySubscriberOn mySubscriberOn = new MySubscriberOn();
//        mySubscriberOn.testSubscribe();
//        System.out.println("---------------");
//        mySubscriberOn.testSubscribeOn();
        mySubscriberOn.testSubscribeOnGroceriesConcurrently2();

    }

}
