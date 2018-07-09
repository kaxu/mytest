package com.kaxudodo.mysort;

public class SortUtil {

    public final static <AnyType extends Comparable<? super AnyType>> void swapReferences(AnyType[] a,int i,int j){
        AnyType tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
