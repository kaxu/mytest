package com.kaxudodo.reactivex;

import rx.Observable;

import java.util.concurrent.TimeUnit;

import static com.kaxudodo.reactivex.ReactiveUtil.log;

/**
 * Created by aaron on 2018/6/28.
 */
public class MyDelay {



    public void test(){
        log("test start ");
        Observable.just("a","b")
//                .repeat(4)
                .delay(1, TimeUnit.SECONDS)
                .subscribe(x -> log(x));
        log("test end");
    }

    public void test1(){
        log("test start ");
        ReactiveUtil.simpleAsync()
//        ReactiveUtil.simple()
//                .delay(1, TimeUnit.SECONDS)
                .subscribe(x -> log(x));
        log("test end");
    }

    public static void main(String[] args) throws InterruptedException {
        MyDelay myDelay = new MyDelay();
        myDelay.test1();
        System.out.println("test1 end");
        Thread.sleep(3000);
        System.out.println("Thread.sleep end");
    }
}
