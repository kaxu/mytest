package com.kaxudodo.reactivex.backpressure;

import rx.Observable;
import rx.schedulers.Schedulers;

import static com.kaxudodo.reactivex.ReactiveUtil.log;

/**
 * Created by aaron on 2018/8/8.
 */
public class Dish {
    private final byte[] oneKb = new byte[1_024];
    private final int id;

    Dish(int id) {
        this.id = id;
        System.out.println("Created: " + id);
    }

    public String toString() {

        return String.valueOf(id);
    }

    public static Observable<Integer> myRange(int from, int count) {
        return Observable.create(subscriber -> {
            int i = from;
            while (i < from + count) {
                if (!subscriber.isUnsubscribed()) {

                    subscriber.onNext(i++);
                } else {

                    return;
                }
            }
            subscriber.onCompleted();

        });

    }

    public static void test1(){
        Observable.range(1, 1_000_000_000)
                .map(Dish::new)
                .observeOn(Schedulers.io())
                .subscribe(x -> {
                    System.out.println("Washing: " + x);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }

    public static void test2(){
        myRange(1, 1_000_000_000)
                .map(Dish::new)
                .onBackpressureBuffer(1000, () -> log("Buffer full"))
                .observeOn(Schedulers.io())
                .subscribe(x -> {
                    System.out.println("Washing: " + x);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }

    public static void main(String[] args) throws InterruptedException {
        test2();

        Thread.sleep(50000);
    }
}
