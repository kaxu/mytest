package com.kaxudodo.dataandalgorithm;

/**
 * Created by aaron on 16/8/9.
 */
public class C14 {

    public static int get2num(int n){
        print10(n);
        return 0;
    }

    public static void print10(int n){
        if(n < 2){
            System.out.print(n);
        }else {
            System.out.print(n % 2);
            print10(n/2);
        }
    }

    public static void main(String[] args) {
        get2num(8);
    }
}
