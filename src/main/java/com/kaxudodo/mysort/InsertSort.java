package com.kaxudodo.mysort;

import javax.sound.midi.Soundbank;
import java.util.Arrays;

/**
 * Created by aaron on 2018/5/25.
 */
public class InsertSort {

    public static <T extends Comparable<? super T>> void myInsertSort(T[] a){
        int j;

        for(int p = 1;p < a.length;p++){
            T tmp = a[p];
            for(j = p;j > 0 && tmp.compareTo(a[j-1]) < 0;j--)
                a[j]=a[j-1];
            a[j] = tmp;
        }

    }

    public static <T extends Comparable<? super T>> void myInsertSort(T[] a,int left,int right){
        int j;

        for(int p = 1+left;p < right+1;p++){
            T tmp = a[p];
            for(j = p;j > left && tmp.compareTo(a[j-1]) < 0;j--)
                a[j]=a[j-1];
            a[j] = tmp;
        }


    }

    public static void main(String[] args) {
        Integer[] numbers = {3,1,4,1,5,9,2,6,5};
//        InsertSort.myInsertSort(numbers);
        InsertSort.myInsertSort(numbers,3,8);
        Arrays.asList(numbers).stream().forEach(e-> System.out.print(e));
    }
}
