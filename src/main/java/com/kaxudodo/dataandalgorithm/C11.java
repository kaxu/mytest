package com.kaxudodo.dataandalgorithm;

import com.kaxudodo.utils.TimeUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Created by aaron on 16/8/7.
 */
public class C11 {

    public static void main(String[] args) {
        int[] a = new int[100000];
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(15);
            System.out.println(a[i]);
        }

        System.out.println("--------------");
        long start = new Date().getTime();
        System.out.println(getK(a));
        System.out.println(TimeUtil.costTime(start,new Date().getTime()));
    }

    private static int getK(int[] a){
        int k = a.length/2;
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        System.out.println("--------------");
//        System.out.println(a.length-k);
        return a[a.length-k];
    }
}
