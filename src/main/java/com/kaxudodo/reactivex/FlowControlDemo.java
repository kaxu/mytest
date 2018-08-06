package com.kaxudodo.reactivex;

import rx.Observable;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.kaxudodo.reactivex.ReactiveUtil.log;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static rx.Observable.just;

/**
 * Created by aaron on 2018/8/6.
 */
public class FlowControlDemo {

    public void test1() {
        long startTime = System.currentTimeMillis();
        Observable.interval(7, MILLISECONDS)
//        Observable.just(1,2,3,4,5,6,7,8)
                .timestamp()
                .sample(1, SECONDS)
                .map(ts -> ts.getTimestampMillis() - startTime + "ms: " + ts.getValue())
                .take(5)
                .subscribe(x -> log(x));
    }

    public void test2(){
        Observable<String> names = just("Mary", "Patricia", "Linda", "Barbara", "Elizabeth", "Jennifer", "Maria", "Susan", "Margaret", "Dorothy");
        Observable<Long> absoluteDelayMillis = just(0.1, 0.6, 0.9,1.1, 3.3, 3.4, 3.5, 3.6, 4.4, 4.8) .map(d -> (long)(d * 1_000));
        Observable<String> delayedNames = names
                .zipWith(absoluteDelayMillis, (n, d) ->
                        just(n)
                        .delay(d, MILLISECONDS)) .flatMap(o -> o);

        delayedNames
                .sample(1, SECONDS)
// the throttleFirst() operator that emits the very first event that appeared in each period.
//                .throttleFirst(1, SECONDS)
                .subscribe(x -> log(x));
    }

    public void test3(){
        Observable
                .range(1, 7) //1, 2, 3, ... 7
                .buffer(3) .subscribe((List<Integer> list) -> { System.out.println(list); } );
    }

    public void test4(){
        Random random = new Random();
        Observable
                .defer(() -> just(random.nextGaussian()))
                .repeat(1000) .buffer(100, 1)
                .map(this::averageOfList)
                .subscribe(System.out::println);
    }

    private double averageOfList(List<Double> list) {
        return list .stream().collect(Collectors.averagingDouble(x -> x));
    }

    public void test5(){
        Observable<List<Integer>> odd = Observable .range(1, 7)
                .buffer(1, 2);
// flatMapIterable() expects a function that transforms each value in the stream (one- element List<Integer>) into a List.
//                .flatMapIterable(list -> list);
        odd.subscribe(System.out::println);
    }

    public void test6(){
        Observable<String> names = just( "Mary", "Patricia", "Linda", "Barbara", "Elizabeth", "Jennifer", "Maria", "Susan", "Margaret", "Dorothy");
        Observable<Long> absoluteDelays = just( 0.1, 0.6, 0.9, 1.1, 3.3, 3.4, 3.5, 3.6, 4.4, 4.8 )
                .map(d -> (long) (d * 1_000));

        Observable<String> delayedNames = Observable
                .zip(names, absoluteDelays, (n, d) -> just(n)
                        .delay(d, MILLISECONDS) )
                .flatMap(o -> o);
        delayedNames
                .buffer(1, SECONDS)
                .subscribe(System.out::println);
    }

    public void test7(){
        Observable<Duration> insideBusinessHours = Observable
                .interval(1, SECONDS)
                .map(x -> Duration.ofMillis(100));
        insideBusinessHours.subscribe(x -> log(x));
    }

    public static void main(String[] args) throws InterruptedException {
        FlowControlDemo flowControlDemo = new FlowControlDemo();
        flowControlDemo.test7();
        Thread.sleep(5000);
    }
}
