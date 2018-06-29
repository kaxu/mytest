package com.kaxudodo.reactivex;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

/**
 * Created by aaron on 2018/6/8.
 */
public class TwitterSubject {

    private final ReplaySubject<Integer> subject = ReplaySubject.create();

    public TwitterSubject() {
//        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
//        twitterStream.addListener(new StatusListener() {
//            @Override public void onStatus(Status status) {
//                subject.onNext(status);
//            }
//
//            @Override public void onException(Exception ex) {
//            subject.onError(ex);
//            }
//        //other callbacks
//        });
//        twitterStream.sample();
        ReactiveExtension.log("构造开始");
        subject.onNext(1);
        subject.onNext(2);
        ReactiveExtension.log("构造结束");
//        subject.onError(new Throwable());

    }

    public Observable<Integer> observe() { return subject;}
    public Observer<Integer> observer() { return subject;}

    public static void main(String[] args) {
        TwitterSubject twitterSubject = new TwitterSubject();
        ReactiveExtension.log("订阅开始");
//        twitterSubject.observe().subscribe(x -> ReactiveExtension.log(x));
//        twitterSubject.observe().subscribe(x -> ReactiveExtension.log(x));
        twitterSubject.observer().onNext(3);
        Observable lazy = twitterSubject.observe().publish().refCount(); //...
        System.out.println("Before subscribers");
        Subscription sub1 = lazy.subscribe(x -> ReactiveExtension.log("1 "+x));
        System.out.println("Subscribed 1");
        Subscription sub2 = lazy.subscribe(x -> ReactiveExtension.log("2 "+x));
        System.out.println("Subscribed 2");
        sub1.unsubscribe();
        System.out.println("Unsubscribed 1");
        sub2.unsubscribe();
        System.out.println("Unsubscribed 2");
    }

}
