package com.kaxudodo.dataandalgorithm;

import java.util.Arrays;

/**
 * Created by aaron on 16/8/13.
 */
public class C226 {

    public static int[] test(int[] a,int n){
        if(n <= 2){
            return a;
        }
        int modnum = n % 2;
        //偶数
        if(modnum == 0){
            int[] b = new int[n/2];
            int j = 0;
            for (int i = 0; i < n; i++) {
                if(a[i] == a[i+1]){
                    b[j] = a[i];
                    j++;
                }
                i++;
            }
            return test(b,j);
        }
        //奇数
        if(modnum == 1){
            int[] b = new int[n/2+1];
            int j = 0;
            for (int i = 0; i < n; i++) {
                //最后一个元素
                if(i == n-1 ){
                    if(a[i] == a[0]){
                        b[j] = a[i];
                        j++;
                    }
                    break;
                }
                if(a[i] == a[i+1]){
                    b[j] = a[i];
                    j++;
                }
                i++;
            }
            return test(b,j);
        }
        return a;
    }

    public static void main(String[] args) {
//        int[] a = {3,3,4,2,4,4,2,4,4};
//        int[] r = test(a,a.length);
//        System.out.println(Arrays.toString(r));
        int[] a = {3,3,4,2,4,4,2,4};
        int[] r = test(a,a.length);
        System.out.println(Arrays.toString(r));
    }
}
