package com.kaxudodo.reactivex;

/**
 * Created by aaron on 2018/6/8.
 */
public class ReactiveUtil {

    public static void log(Object msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }

    public static void log(Object label,long start) {
        System.out.println( System.currentTimeMillis() - start +
                    "\t| " + Thread.currentThread().getName()
                    + "\t| " + label);
    }
}
