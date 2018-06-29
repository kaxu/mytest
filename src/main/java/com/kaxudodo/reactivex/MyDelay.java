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
                .delay(1, TimeUnit.SECONDS)
                .subscribe(x -> log(x));
        log("test end");
    }

    public static void main(String[] args) throws InterruptedException {
//        MyDelay myDelay = new MyDelay();
//        myDelay.test();
//        Thread.sleep(3000);

    }
}
