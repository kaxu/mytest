package com.kaxudodo.utils;

import org.apache.commons.lang3.RandomUtils;

public class RandomArrayUtil {

    public static Integer[] getRandomArray(int count){
        if(count<=0)
            count = 20;
        Integer[] a = new Integer[count];
        for (int i = 0; i < count; i++) {
            a[i] = RandomUtils.nextInt(1,count*2);
        }
        return a;
    }

}
