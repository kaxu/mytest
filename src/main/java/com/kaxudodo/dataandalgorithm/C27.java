package com.kaxudodo.dataandalgorithm;

import com.kaxudodo.utils.TimeUtil;

import java.util.Date;

/**
 * Created by aaron on 16/8/11.
 */
public class C27 {

    public static void test1(int n){
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum++;
        }
        System.out.println(TimeUtil.costTime(start,System.currentTimeMillis()));
    }

    public static void test2(int n){
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                sum++;
            }
        }
//        System.out.println(sum);
        System.out.println(TimeUtil.costTime(start,System.currentTimeMillis()));
    }

    public static void test3(int n){
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n*n; j++) {
                sum++;
            }
        }
        System.out.println(TimeUtil.costTime(start,System.currentTimeMillis()));
    }

    public static void main(String[] args) {
        int n = 100;
        for (int i = 1; i < 5; i++) {
            test1(n);
            n = n * 10;
        }
        System.out.println("---------------");
        n = 100;
        for (int i = 1; i < 5; i++) {
            test2(n);
            n = n * 10;
        }
        System.out.println("---------------");
        n = 100;
        for (int i = 1; i < 5; i++) {
            System.out.println(n);
            test3(n);
            n = n * 10;
        }
    }

}
