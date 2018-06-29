package com.kaxudodo.reactivex;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

/**
 * Created by aaron on 2018/6/11.
 */
public class Myscan {

    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5)
                .reduce(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer sum, Integer item) {
                        return sum + item;
                    }
                }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }
        });
    }
}
