package com.kaxudodo.dataandalgorithm;

/**
 * Created by aaron on 16/8/14.
 */
public class C232 {

    public static int test (int[] a,int x){
        int low=0,high=a.length-1;
        while (low <= high){
            int mid = (low+high)/2;

            if(a[mid] < x){
                low = mid +1;
                continue;
            }else if(a[mid] > x){
                high = mid -1;
                continue;
            }
            return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7,7};
        int x = 7;
        System.out.println(test(a,x));
    }
}
