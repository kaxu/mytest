package com.kaxudodo.dataandalgorithm;

import java.util.Arrays;

/**
 * Created by aaron on 16/8/13.
 */
public class C221 {

    public static void test(int n){
        int[] a = new int[n-1];
        for (int i = 0; i < a.length; i++) {
            a[i] = i+2;
        }
//        System.out.println(Arrays.toString(a));

        int b = 2;
        while (b <= Math.sqrt(n)){
            if(a[b-2] != 0)
                System.out.println(b);
            else{
                b++;
                continue;
            }
            for (int i = 2; b*i <= n; i++) {
                a[b*i-2] = 0;
            }
            b++;
        }
        System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        test(100);
    }

}
