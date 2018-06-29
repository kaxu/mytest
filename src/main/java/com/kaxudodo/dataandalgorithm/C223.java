package com.kaxudodo.dataandalgorithm;

/**
 * Created by aaron on 16/8/12.
 */
public class C223 {

    public static long pow(long x,int n){
        if(n == 0)
            return 1;
        if(n == 1)
            return x;
        long a = 1;
        while (n >= 2){
            if((n % 2) == 1){
                a = a * x;
            }
            x = x * x;
            n = n / 2;
        }
        return a*x;
    }

    public static void main(String[] args) {
        System.out.println(pow(2,8));
    }
}
