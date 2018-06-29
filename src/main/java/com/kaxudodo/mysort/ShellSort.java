package com.kaxudodo.mysort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by aaron on 2018/4/13.
 */
public class ShellSort {

    public static <T extends  Comparable<? super T>> void shellsort(T[] a){
        int j;
        for(int gap = a.length / 2; gap> 0;gap /= 2){

            System.out.println("gap="+gap);
            int counti = 0;
            for(int i = gap;i < a.length ; i++){
                counti++;
                T tmp = a[i];
                for(j = i;j >= gap && tmp.compareTo( a[j-gap]) < 0;j-=gap)
                    a[j] = a[j -gap ];
                a[j] = tmp;
            }
            System.out.println(counti);
        }
        System.out.println(Arrays.asList(a));
    }

    public static <T extends  Comparable<? super T>> void shellsortByHibbard(T[] a){
        int j;
        int[] gap = {7,3,1};
        for( int g:gap){

            System.out.println("gap="+g);
            int counti = 0;
            for(int i = g;i < a.length ; i++){
                counti++;
                T tmp = a[i];
                for(j = i;j >= g && tmp.compareTo( a[j-g]) < 0;j-=g)
                    a[j] = a[j -g ];
                a[j] = tmp;
            }
            System.out.println(counti);
        }
        System.out.println(Arrays.asList(a));
    }

    public static void main(String[] args) {
        Integer[] a = {Integer.valueOf(44),
                        Integer.valueOf(94),
                        Integer.valueOf(11),
                        Integer.valueOf(96),
                        Integer.valueOf(12),
                        Integer.valueOf(35),
                        Integer.valueOf(17),
                        Integer.valueOf(95),
        };
        shellsort(a);

        Integer[] a2 = {Integer.valueOf(9),
                Integer.valueOf(8),
                Integer.valueOf(7),
                Integer.valueOf(6),
                Integer.valueOf(5),
                Integer.valueOf(4),
                Integer.valueOf(3),
                Integer.valueOf(2),
                Integer.valueOf(1),
        };
        shellsortByHibbard(a2);
    }
}
