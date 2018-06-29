package com.kaxudodo.dataandalgorithm;

/**
 * Created by aaron on 2018/4/3.
 */
public class UniversalHash {

    public static final int DIGS = 31;
    public static final int mersennep = (1 << DIGS) -1;

    public static int universalHash(int x, int A, int B, int M){
        long hashVal = (long)A * x + B;

        hashVal = ( (hashVal >> DIGS) + (hashVal & mersennep));
        if(hashVal >= mersennep)
            hashVal -= mersennep;

        return (int) hashVal % M;
    }

    public static void main(String[] args) {
        long hashVal = 100;
        hashVal = hashVal >> 3;
        long hashVal2 = 100;
        hashVal2 = hashVal & (1<<3-1);
        System.out.println(hashVal2);
    }

}
