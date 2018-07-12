package com.kaxudodo.reactivex;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import rx.Single;
import rx.SingleSubscriber;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.time.Instant;

/**
 * Created by aaron on 2018/7/12.
 */
public class MySingle {

    public static void test() {
        Single<String> single = Single.just("Hello, world!");
        single.subscribe(System.out::println);

        Single<Instant> error =

                Single.error(new RuntimeException("Opps!"));
        error.observeOn(Schedulers.io()).subscribe(System.out::println, Throwable::printStackTrace);
    }

    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

    Single<Response> fetch(String address) {
        return Single.create(subscriber -> asyncHttpClient
                .prepareGet(address)
                .execute(handler(subscriber)));

    }


    AsyncCompletionHandler handler(SingleSubscriber<? super Response> subscriber) {
        return new AsyncCompletionHandler() {
            public Response onCompleted(Response response) {
                subscriber.onSuccess(response);
                return response;
            }

            public void onThrowable(Throwable t) {
                subscriber.onError(t);
            }
        };
    }

    Single<String> body(Response response) {
        return Single.create(subscriber -> {
            try {
                subscriber.onSuccess(response.getResponseBody());
            } catch (IOException e) {
                subscriber.onError(e);
            }
        });

    }

    //Same functionality as body():
    Single<String> body2(Response response) {
        return Single.fromCallable(() -> response.getResponseBody());
    }

    public void test2(){
        Single<String> example = fetch("http://www.baidu.com")
                .flatMap(this::body);
        String b = example.toBlocking().value();
        System.out.println(b);
    }

    public void test3(){
        Single<Integer> ignored = Single .just(1)
                .toObservable()
                .ignoreElements() //PROBLEM
                .toSingle();
        ignored.subscribe();
    }

    public static void main(String[] args) {
//        test();
        MySingle mySingle = new MySingle();
        mySingle.test3();
    }
}
