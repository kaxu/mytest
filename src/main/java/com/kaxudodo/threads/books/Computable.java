package com.kaxudodo.threads.books;

/**
 * Created by aaron on 2018/8/22.
 */
public interface Computable<A,V> {
    V compute(A arg) throws InterruptedException;
}
