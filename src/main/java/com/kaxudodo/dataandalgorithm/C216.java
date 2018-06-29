package com.kaxudodo.dataandalgorithm;

/**
 * Created by aaron on 16/8/11.
 */
public class C216 {

    public static long gcd(long m,long n){
        while (n != 0){
            long rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }

    public static long gcd1(long m,long n){
        if((m % 2) == 0 && (n % 2) == 0)
            return 2*gcd1(m / 2,n / 2);
        while (n != 0){
            long rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }

    public static void main(String[] args) {
        System.out.println(gcd1(100,24));
    }
}
