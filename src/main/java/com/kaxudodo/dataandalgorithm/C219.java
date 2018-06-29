package com.kaxudodo.dataandalgorithm;

/**
 * Created by aaron on 16/8/12.
 */
public class C219 {

    public static int maxSubSum4(int[] a){
        int maxSum= 0,thisSum = 0;
        int begin = 0;
        int end = 0;
        for (int i = 0; i < a.length; i++) {
            thisSum += a[i];
            if(thisSum > maxSum){
                maxSum = thisSum;
                end = i;
            }
            else if (thisSum < 0){
                thisSum = 0;
                if(i != a.length-1)
                    begin = i + 1;
            }
        }
        System.out.println("maxSum:"+maxSum);
        System.out.println("from:"+begin+"  to:"+end);
        return maxSum;
    }

    public static void main(String[] args) {
        int[] a = {1,2,-1,3,4,-7,9,1,1,2,-4,-19};
        maxSubSum4(a);
    }
}
