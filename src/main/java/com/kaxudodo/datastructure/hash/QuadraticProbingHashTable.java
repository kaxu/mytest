package com.kaxudodo.datastructure.hash;

import com.kaxudodo.utils.HashUtil;

import java.math.BigInteger;

/**
 * Created by aaron on 2018/7/2.
 */
public class QuadraticProbingHashTable<AnyType> {

    private static class HashEntry<AnyType>{
        public AnyType element;
        public boolean isActive;
        public HashEntry(AnyType e){
            this(e,true);
        }
        public HashEntry(AnyType e,boolean i){
            element = e;isActive =i;
        }

    }

    private static final int DEFAULT_TABLE_SIZE = 11;
    private int currentSize;
    private HashEntry<AnyType>[] array;

    public QuadraticProbingHashTable(){
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticProbingHashTable(int size){
        allocateArray(size);
        makeEmpty();
    }

    public void makeEmpty(){
        currentSize = 0;
        for(int i = 0;i<array.length;i++)
            array[i] = null;
    }

    private void allocateArray(int arraySize){
        array = new HashEntry[nextPrime(arraySize)];
    }

    private static int nextPrime(int n){
        BigInteger bi1 = new BigInteger(String.valueOf(n));
        // assign nextProbablePrime value of bi1 to bi2
        BigInteger bi2 = bi1.nextProbablePrime();
        return bi2.intValue();
    }

    public boolean contains(AnyType x){
        int currentPos = findPos(x);
        return isActive(currentPos);
    }

    public AnyType find(AnyType x){
        return array[findPos(x)].element;
    }

    private int findPos(AnyType x){
        int offset = 1;
        int currentPos = myhash(x);
        while(array[currentPos] != null && !array[currentPos].element.equals(x)){
            currentPos+=offset;
            offset+=2;
            if(currentPos>=array.length)
                currentPos-=array.length;
        }
        return currentPos;
    }



    private boolean isActive(int currentPos){
        return array[currentPos] != null && array[currentPos].isActive;
    }

    public void insert(AnyType x){
        int currentPos = findPos(x);
        if(isActive(currentPos))
            return;
        array[currentPos] = new HashEntry<AnyType>(x,true);
        if(currentSize>array.length/2)
            rehash();
    }

    public void remove(AnyType x){
        int currentPos = findPos(x);
        if(isActive(currentPos))
            array[currentPos].isActive = false;
    }

    private void rehash(){
        HashEntry<AnyType>[] oldArray = array;
        allocateArray(nextPrime(2*oldArray.length));
        currentSize=0;
        for (int i = 0; i < oldArray.length; i++) {
            if(oldArray[i]!=null && oldArray[i].isActive)
                insert(oldArray[i].element);
        }
    }

    private int myhash(AnyType x){
        int a = 51;
        int b = 151;
        int m = array.length;
        return HashUtil.universalHash(x.hashCode(),a,b,m);
    }

}
