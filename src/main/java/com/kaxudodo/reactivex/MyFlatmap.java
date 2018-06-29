package com.kaxudodo.reactivex;

import org.apache.commons.lang3.RandomUtils;
import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by aaron on 2018/6/13.
 */
public class MyFlatmap {

    public static void main(String[] args) {
//        System.out.println(RandomUtils.nextInt(1,3));
        long start = System.currentTimeMillis();
        Observable.just(1, 2, 3, 4, 5,6,7,8)
                .flatMap(x->Observable.just(x).delay(1, TimeUnit.MILLISECONDS))
                .subscribe(x -> ReactiveUtil.log(x,start));

        try {
            Thread.sleep(12);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
