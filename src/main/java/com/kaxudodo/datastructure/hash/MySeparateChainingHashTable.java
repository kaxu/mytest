package com.kaxudodo.datastructure.hash;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by aaron on 2018/6/11.
 */
public class MySeparateChainingHashTable<AnyType> {

    private static final int DEFAULT_TABLE_SIZE = 101;
    private List<AnyType>[] theList;
    private int currentSize;

    private static int nextPrime(int n){
        return 101;
    }

    private int myhash(AnyType x){
        int hashVal = x.hashCode();
        hashVal %= theList.length;
        if(hashVal < 0 )
            hashVal += theList.length;
        return hashVal;
    }

    public MySeparateChainingHashTable(int size){
        theList = new LinkedList[nextPrime(size)];
        for(int i=0;i<theList.length;i++){
            theList[i] = new LinkedList<>();
        }
    }

    public void makeEmpty(){
        for(int i=0;i<theList.length;i++){
            theList[i].clear();
        }
        currentSize=0;
    }

    public boolean contains(AnyType x){
        List<AnyType> whichList = theList[myhash(x)];
        return whichList.contains(x);
    }

    public void insert(AnyType x){
        List<AnyType> whichList = theList[myhash(x)];
        if(!whichList.contains(x)){
            whichList.add(x);
            if(++currentSize>theList.length)
                System.out.println("rehash()");
        }
    }

    public void remove(AnyType x){
        List<AnyType> whichList = theList[myhash(x)];
        if(whichList.contains(x)){
            whichList.remove(x);
            currentSize--;
        }
    }
}
