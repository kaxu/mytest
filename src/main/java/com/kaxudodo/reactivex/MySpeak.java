package com.kaxudodo.reactivex;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.Pair;
import rx.Observable;

import java.util.Calendar;
import java.util.Random;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static rx.Observable.just;

/**
 * Created by aaron on 2018/6/12.
 */
public class MySpeak {

    Observable<String> speak(String quote, long millisPerChar) {
        String[] tokens = quote.replaceAll("[:,]", "").split(" ");
        Observable<String> words = Observable.from(tokens);
        Observable<Long> absoluteDelay = words .map(String::length)
                .map(len -> len * millisPerChar)
                .scan((total, current) -> total + current);
        return words .zipWith(absoluteDelay.startWith(0L), Pair::of)
                .flatMap(pair -> just(pair.getLeft())
                        .delay(pair.getRight(), MILLISECONDS));

    }

    public static void main(String[] args) {
        MySpeak mySpeak = new MySpeak();
//        Observable<String> words = mySpeak.speak("We construct a sequence of pair words",100);

        Observable<String> alice = mySpeak.speak("To be, or not to be: that is the question", 110);
        Observable<String> bob = mySpeak.speak("Though this be madness, yet there is method in't", 90);
        Observable<String> jane = mySpeak.speak("There are more things in Heaven and Earth, " + "Horatio, than are dreamt of in your philosophy", 100);

//        Observable .concat(
//                alice.map(w -> "Alice: " + w),
//                bob.map(w -> "Bob: " + w),
//                jane.map(w -> "Jane: " + w) )
//                .subscribe(System.out::println);

        Random rnd = new Random();
        Observable<Observable<String>> quotes = just(
                alice.map(w -> Calendar.getInstance().get(Calendar.SECOND)+" Alice: " + w),
                bob.map(w -> Calendar.getInstance().get(Calendar.SECOND)+" Bob: " + w),
                jane.map(w -> Calendar.getInstance().get(Calendar.SECOND)+" Jane: " + w))
                .flatMap(innerObs -> just(innerObs).delay(rnd.nextInt(5), SECONDS));
//                .map(innerObs -> innerObs.delay(rnd.nextInt(5), SECONDS));

        Observable .switchOnNext(quotes) .subscribe(System.out::println);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
