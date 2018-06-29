package com.kaxudodo.reactivex;


import rx.Observable;
import rx.observables.ConnectableObservable;

/**
 * Created by aaron on 2018/6/8.
 */
public class MyConnectableObservable {

    private final ConnectableObservable<Integer> observable =
            Observable.<Integer>create(subscriber -> {
                ReactiveUtil.log("Starting");
                subscriber.onNext(3);
                subscriber.onNext(4);
            }).publish();

    public static void main(String[] args) {
//        MyConnectableObservable myConnectableObservable = new MyConnectableObservable();
//        myConnectableObservable.observable.subscribe(x -> ReactiveUtil.log("1 "+x));
//        myConnectableObservable.observable.connect();
//        myConnectableObservable.observable.subscribe(x -> ReactiveUtil.log("2 "+x));

        ConnectableObservable firstMillion  = (ConnectableObservable) Observable.range( 1, 1000000 )
                .sample(7, java.util.concurrent.TimeUnit.MILLISECONDS).publish();
        firstMillion.subscribe(x -> ReactiveUtil.log("#1 "+x));
        firstMillion.subscribe(x -> ReactiveUtil.log("#2 "+x));
        firstMillion.connect();
    }
}
