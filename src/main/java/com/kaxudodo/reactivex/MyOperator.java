package com.kaxudodo.reactivex;

import rx.Observable;
import rx.Subscriber;
import rx.internal.operators.OperatorDistinct;
import rx.internal.operators.OperatorScan;
import rx.internal.operators.OperatorSingle;

/**
 * Created by aaron on 2018/6/14.
 */
public class MyOperator {

    public <T> Observable.Operator<String, T> toStringOfOdd() {
        return new Observable.Operator<String, T>() {
            private boolean odd = true;

            @Override
            public Subscriber<? super T> call(Subscriber<? super String> child) {
                return new Subscriber<T>(child) {
                    @Override
                    public void onCompleted() {
                        child.onCompleted();
                    }

                    @Override public void onError(Throwable e) {
                        child.onError(e);
                    }

                    @Override public void onNext(T t) {
                        if(odd) {
                            child.onNext(t.toString());
                        } else {
                            request(1);
                        } odd = !odd;
                    }
                };
            }
        };
    }

    // 这个无参的构造函数(无child)写法是错误的,会导致unsubscribe()方法不会往源头stream传递
    public <T> Observable.Operator<String, T> toStringOfOdd2() {
        return child -> new Subscriber<T>() {
            private boolean odd = true;

            @Override
            public void onCompleted() {
                child.onCompleted();
            }

            @Override public void onError(Throwable e) {
                child.onError(e);
            }

            @Override public void onNext(T t) {
                if(odd) {
                    child.onNext(t.toString());
                } else {
                    request(1);
                } odd = !odd;
            }
        };
    }

    public static void main(String[] args) {
        MyOperator myOperator = new MyOperator();

        Observable<String> odd = Observable .range(1, 9).repeat() .lift(myOperator.toStringOfOdd()).take(3);
        odd.subscribe(System.out::println);
    }
}
