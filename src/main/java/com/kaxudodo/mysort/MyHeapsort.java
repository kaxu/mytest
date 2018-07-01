package com.kaxudodo.mysort;

import com.kaxudodo.utils.RandomArrayUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class MyHeapsort {

    private static int leftChild(int i){
        return 2*i+1;
    }

    private static <AnyType extends Comparable<? super AnyType>> void percDown(AnyType[] a,int i,int n){
        int child=0;
        AnyType tmp;
        for (tmp = a[i]; leftChild(i) < n; i=child) {
            child = leftChild(i);
            if(child !=n-1 && a[child].compareTo(a[child+1])<0)
                child++;
            if(tmp.compareTo(a[child])<0)
                a[i]=a[child];
            else
                break;
        }
        a[i]=tmp;
    }



    private static <AnyType extends Comparable<? super AnyType>> void swapReferences(AnyType[] a,int i,int j){
        AnyType tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }


    public static <AnyType extends Comparable<? super AnyType>> void heapsort(AnyType[] a){
        // buildHeap
        for(int i = a.length /2 -1;i>=0;i--)
            percDown(a,i,a.length);

        // deleteMax
        for(int i = a.length-1;i>0;i--){
            swapReferences(a,0,i);
            percDown(a,0,i);
        }
    }

    /**
     * 对固定区间进行排序
     * @param i
     * @return
     */

    private static int leftChildForLow(int i,int _low){
//        int _low = 1;
        return 2*(i)+1-_low;
    }

    private static <AnyType extends Comparable<? super AnyType>> void percDownForLow(AnyType[] a,int i,int n,int _low){
        int child=0;
        AnyType tmp;
        for (tmp = a[i]; leftChildForLow(i,_low) < n; i=child) {
            child = leftChildForLow(i,_low);
            if(child !=n-1 && a[child].compareTo(a[child+1])<0)
                child++;
            if(tmp.compareTo(a[child])<0)
                a[i]=a[child];
            else
                break;
        }
        a[i]=tmp;
    }

    public static <AnyType extends Comparable<? super AnyType>> void heapsort(AnyType[] a,int low,int high){
        int _low = low-1;
        int _high = high-1;
        int length = (high-low+1);
        // buildHeap
        for(int i = length /2-1+_low;i>=_low;i--)
            percDownForLow(a,i,high,_low);
        System.out.println("---- "+Arrays.toString(a));

        // deleteMax
        for(int i = _high;i>_low;i--){
            swapReferences(a,_low,i);
            percDownForLow(a,_low,i,_low);
            System.out.println("****** "+Arrays.toString(a));
        }
    }

    public static void main(String[] args) {
        Integer[] a = RandomArrayUtil.getRandomArray(20);
        Integer[] b = a;
//        heapsort(a);
//        System.out.println(Arrays.toString(a));
        heapsort(b,2,10);
        System.out.println(Arrays.toString(b));




    }
}
