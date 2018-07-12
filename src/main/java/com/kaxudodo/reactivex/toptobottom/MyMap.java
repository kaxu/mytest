package com.kaxudodo.reactivex.toptobottom;

import rx.Observable;

import static com.kaxudodo.reactivex.ReactiveUtil.log;

/**
 * Created by aaron on 2018/7/10.
 */
public class MyMap {

    public static void main(String[] args) {
        Observable<String> alphabet = Observable .range(0, 'Z' - 'A' + 1) .map(c -> c+"ss");
        System.out.println("start");
        alphabet.subscribe(x->log(x));
        System.out.println("end");

    }
}
