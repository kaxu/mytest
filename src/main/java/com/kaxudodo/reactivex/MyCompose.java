package com.kaxudodo.reactivex;

import org.apache.commons.lang3.tuple.Pair;
import rx.Observable;

import static rx.Observable.just;

/**
 * Created by aaron on 2018/6/13.
 */
public class MyCompose {

    private <T> Observable.Transformer<T, T> odd() {
        Observable<Boolean> trueFalse = just(true, false).repeat();
        return upstream -> upstream
                .zipWith(trueFalse, Pair::of)
                .filter(Pair::getRight)
                .map(Pair::getLeft); }

    public static void main(String[] args) {
        MyCompose myCompose = new MyCompose();
        //[A, B, C, D, E...]
        Observable<Character> alphabet = Observable .range(0, 'Z' - 'A' + 1) .map(c -> (char) ('A' + c));
        //[A, C, E, G, I...]
        alphabet .compose(myCompose.odd()) .forEach(System.out::println);
    }
}
