package com.kaxudodo.dataandalgorithm;

/**
 * Created by aaron on 16/8/11.
 */
public class C217 {

    public static int sum(int[] a){
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            if(sum < 0 && a[i] < 0){
                sum = a[i];
            }
            //子序列为正,后续为正,会越来越大
            if(sum > 0 && a[i] > 0){
                sum = a[i];
            }
            //子序列为正,后续为负
            if(sum >= 0 && a[i] <= 0){
                if(-a[i] > sum ){
                    if(-(sum+a[i]) <= sum)
                        sum = sum + a[i];
                    else
                        sum = a[i];
                }else {
                    sum = sum + a[i];
                }
            }
            if(sum <= 0 && a[i] >= 0){
                if(-sum >= a[i] ){
                    sum = sum + a[i];
                }else {
                    if((sum+a[i]) <= -sum)
                        sum = sum + a[i];
                    else
                        sum = a[i];
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] a = {1,2,-3,4,5,6,-8};
        System.out.println(sum(a));
    }
}
