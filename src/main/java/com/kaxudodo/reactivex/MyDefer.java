package com.kaxudodo.reactivex;

import org.apache.commons.lang3.RandomUtils;
import rx.Observable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 2018/6/15.
 */
public class MyDefer {

    public static List<Integer> getRandomList(){
        System.out.println("into getRandomList");
        List<Integer> randomList = new ArrayList<>();
        randomList.add(RandomUtils.nextInt(1,4));
        randomList.add(RandomUtils.nextInt(1,4));
        randomList.add(RandomUtils.nextInt(1,4));
        randomList.add(RandomUtils.nextInt(1,4));
//        System.out.println("end");
        return randomList;
    }

    public static void main(String[] args) {
        System.out.println("lazy");
        Observable.defer(() ->
        Observable.from(getRandomList()));

//        Observable.range(1,10);
        System.out.println("lazy end");
        System.out.println("eager");
        Observable.from(getRandomList());
        System.out.println("eager end");
    }
}
