package com.kaxudodo.dataandalgorithm;

/**
 * Created by aaron on 16/8/11.
 */
public class C29 {

    public static int maxSubSum4(int[] a){
        int maxSum = 0,thisSum = 0;
        for (int i = 0; i < a.length; i++) {
            thisSum += a[i];
            if(thisSum > maxSum)
                maxSum = thisSum;
            else if(thisSum < 0)
                thisSum = 0;
        }
        return maxSum;
    }
}
