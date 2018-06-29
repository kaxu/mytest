package com.kaxudodo.dataandalgorithm;

/**
 * Created by aaron on 16/8/13.
 */
public class C228 {

    public static void test(int[] a){
        int i = 0;
        int best = 0;
        for (int j = 0; j < a.length; j++) {
            if(best <= (a[j] - a[i])){
                best = a[j] - a[i];
            }
            //遇到新的最小的数就更新.前面的留下的best肯定是前面一段数组能够取得的最大差了.
            if(a[j] < a[i]){
                i = j;
            }
        }
        System.out.println(best);
    }

    public static void main(String[] args) {
        int[] a = {3,4,10,1,6,7,2,8,9};
        test(a);
    }
}
