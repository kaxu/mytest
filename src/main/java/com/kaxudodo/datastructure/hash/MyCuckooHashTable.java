package com.kaxudodo.datastructure.hash;

import java.util.List;

/**
 * Created by aaron on 2018/7/6.
 */
public class MyCuckooHashTable<AnyType> {

    private HashFamily<AnyType> hashFamily=new MyHashFamily();

    private static final double MAX_LOAD = 0.5;
    private static int DEFAULT_TABLE_SIZE = 101;

//    private AnyType[] array1;
//    private AnyType[] array2;

    private List<AnyType[]> arrays;

    private int currentSize;

    public boolean insert(AnyType x){
        return false;
    }

    public boolean contains(AnyType x){
        return false;
    }

    private int findPos(AnyType x){
        return -1;
    }

    private void rehash(){

    }



}
