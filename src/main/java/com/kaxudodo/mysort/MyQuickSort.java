package com.kaxudodo.mysort;

import java.util.Arrays;

import static com.kaxudodo.mysort.SortUtil.swapReferences;

public class MyQuickSort {

    private static int CUTOFF = 10;

    public static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a){
        quicksort(a,0,a.length-1);
    }

    private static <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType[] a,int left,int right){
        int center = (left+right)/2;
        if(a[center].compareTo(a[left])<0)
            swapReferences(a,left,center);
        if(a[right].compareTo(a[left])<0)
            swapReferences(a,left,right);
        if(a[right].compareTo(a[center])<0)
            swapReferences(a,center,right);
        // place pivot at position right-1
        swapReferences(a,center,right-1);
        return a[right-1];
    }

    private static <AnyType extends Comparable<? super AnyType>> void quicksortOld(AnyType[] a,int left,int right){

        if(left + CUTOFF > right){
            InsertSort.myInsertSort(a,left,right);
            return;
        }else {
            AnyType pivot = median3(a,left,right);
            int i = left,j=right-1;

            for(;;){
                while(a[++i].compareTo(pivot)<0){}
                while(a[--j].compareTo(pivot)>0){}
                if(i<j)
                    swapReferences(a,i,j);
                else
                    break;
            }
            swapReferences(a,i,right-1);
            quicksort(a,left,i-1);
            quicksort(a,i+1,right);
        }

    }

    private static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a,int left,int right){

        while (true){
            if(left + CUTOFF > right){
                InsertSort.myInsertSort(a,left,right);
                return;
            }else {
                AnyType pivot = median3(a,left,right);
            int i = left,j=right-1;
                for(;;){
                    while(a[++i].compareTo(pivot)<0){}
                    while(a[--j].compareTo(pivot)>0){}
                    if(i<j)
                        swapReferences(a,i,j);
                    else
                        break;
                }
                swapReferences(a,i,right-1);
                quicksort(a,left,i-1);
                left=i+1;
            }
        }

    }

    public static void main(String[] args) {
        Integer[] numbers = {3,1,12,89,11,4,1,5,9,2,6,5,8};
//        InsertSort.myInsertSort(numbers);
        MyQuickSort.quicksort(numbers);
        Arrays.asList(numbers).stream().forEach(e-> System.out.print(e+" "));
    }
}
