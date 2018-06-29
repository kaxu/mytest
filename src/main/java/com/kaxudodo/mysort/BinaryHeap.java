package com.kaxudodo.mysort;

import java.util.Arrays;

/**
 * Created by aaron on 2018/5/31.
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {

    private int currentSize;
    private AnyType[] array;
    private static int DEFAULT_CAPACITY =40;

    public BinaryHeap(){
        currentSize = 0 ;
        array = (AnyType[]) new Comparable[DEFAULT_CAPACITY];
    }

    public void enlargeArray(int newSize){

    }

    public AnyType[] getArray(){
        return array;
    }

    public void insert(AnyType x){
        if(currentSize == array.length-1)
            enlargeArray(array.length * 2 + 1);

        int hole = ++currentSize;
        for( array[0] = x;x.compareTo(array[hole / 2]) < 0;hole /= 2)
            array[hole] = array[hole/2];
        array[hole] = x;
    }

    private void percolateDown(int hole){
        int child;
        AnyType tmp = array[hole];
        for(;hole*2<=currentSize;hole=child){
            child = hole*2;
            if(child!=currentSize && array[child+1].compareTo(array[child]) < 0)
                child++;
            if(array[child].compareTo(tmp) < 0)
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = tmp;
    }

    public AnyType deleteMin(){
        AnyType minItem =findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    public AnyType findMin(){
        return array[1];
    }


    public static void main(String[] args) {
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>();
        Integer[] numbers = {10,12,1,14,6,5,8,15,3,9,7,4,11,13,2};
        for(Integer a:numbers){
            binaryHeap.insert(a);
        }
//        Arrays.asList(binaryHeap.getArray()).stream().forEach(e-> System.out.println(e));
        for (int i = 0; i < 3; i++) {
            binaryHeap.deleteMin();
        }
        Arrays.asList(binaryHeap.getArray()).stream().forEach(e-> System.out.println(e));
    }
}
