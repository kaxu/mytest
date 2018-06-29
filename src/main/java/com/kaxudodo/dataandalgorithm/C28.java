package com.kaxudodo.dataandalgorithm;

import com.kaxudodo.utils.TimeUtil;

import java.util.Random;

/**
 * Created by aaron on 16/8/11.
 */
public class C28 {

    public static void test1(int n){
        long start = System.currentTimeMillis();
        Random random = new Random();
        int ran = 0;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            boolean isnotsame = true;
            while (isnotsame){
                ran = random.nextInt(n);
                for (int j = 0; j < i; j++) {
                    if(a[j] == ran)
                        isnotsame = false;
                }
                if(isnotsame == false)
                    isnotsame = true;
                else
                    isnotsame = false;
            }

            a[i] = ran;
        }
//        System.out.println(Arrays.toString(a));
        System.out.println(TimeUtil.costTime(start,System.currentTimeMillis()));
    }

    public static void test2(int n){
        long start = System.currentTimeMillis();
        Random random = new Random();
        int ran = 0;
        int[] a = new int[n];
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {
            ran = random.nextInt(n);
            while(used[ran]){
                ran = random.nextInt(n);
            }
            a[i] = ran;
            used[ran] = true;

        }
//        System.out.println(Arrays.toString(a));
        System.out.println(TimeUtil.costTime(start,System.currentTimeMillis()));
    }

    public static void test3(int n){
        long start = System.currentTimeMillis();
        Random random = new Random();
        int ran = 0;
        int[] a = new int[n];
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {
            a[i] = i+1;
        }
        for (int i = 0; i < n; i++) {
            swap(a,i,random.nextInt(i+1));
        }
//        System.out.println(Arrays.toString(a));
        System.out.println(TimeUtil.costTime(start,System.currentTimeMillis()));
    }

    private static void swap(int[] a ,int s,int e){
        int c = a[s];
        a[s] = a[e];
        a[e] = c;
    }

    public static void main(String[] args) {

        int start = 250;
        for (int i = 0; i < 4; i++) {
            test1(start*(i+1));
        }
        System.out.println("---------------");
        start = 25000;
        for (int i = 0; i < 6; i++) {
            test2(start*(i+1));
        }
        System.out.println("---------------");
        start = 100000;
        for (int i = 0; i < 7; i++) {
            test3(start*(i+1));
        }
    }

}
