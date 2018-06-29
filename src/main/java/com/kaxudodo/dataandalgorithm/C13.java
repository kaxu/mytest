package com.kaxudodo.dataandalgorithm;

/**
 * Created by aaron on 16/8/8.
 */
public class C13 {

    public static void printDigit(int n){
        System.out.println(n);
    }

    public static void printInt(int n){
        if( n >= 10){
            printInt(n/10);
        }
        printDigit((int) (n % 10));
    }

    public static void printDouble(double d,int decPlace){
        if(d < 0){
            System.out.println("-");
            d = -d;
        }

        int id = (int) d;
        printInt(id);
        double r = d - id;
        System.out.println(".");
        int i = 0;
        while (i < decPlace){
            r*=10;
            i++;
        }
        printInt((int)r);
    }

    public static void main(String[] args) {
        printDouble(-1001.123456,4);
//        System.out.println(String.valueOf(-100));
    }
}
