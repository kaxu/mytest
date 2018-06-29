package com.kaxudodo.reactivex;

import rx.Observable;

import java.math.BigDecimal;

import static com.kaxudodo.reactivex.ReactiveUtil.log;

/**
 * Created by aaron on 2018/6/26.
 */
public class RxGroceries {

    Observable<BigDecimal> purchase(String productName, int quantity) {
        return Observable.fromCallable(() -> doPurchase(productName, quantity));
    }

    BigDecimal doPurchase(String productName, int quantity) {
        log("Purchasing " + quantity + " " + productName);
        //real logic here
        log("Done " + quantity + " " + productName);
        return BigDecimal.valueOf(quantity);
    }
}
