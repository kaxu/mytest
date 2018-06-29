package com.kaxudodo.mysort;

/**
 * Created by aaron on 2018/4/18.
 */
public class CountingRadixSort {

    public static void countingRadixSort(String[] arr,int stringLen){

        final int BUCKETS = 256;
        int N = arr.length;
        String[] buffer = new String[N];

        String[] in = arr;
        String[] out = buffer;

        for(int pos = stringLen - 1;pos >= 0; pos--){
            int[] count = new int[BUCKETS + 1];
            for(int i = 0;i<N;i++)
                count[in[i].charAt(pos)+1]++;

            for(int b=1;b<=BUCKETS;b++)
                count[b] += count[b-1];

            for(int i=0;i < N;i++)
                out[count[in[i].charAt(pos)]++] = in[i];

            String[] tmp = in;
            in = out;
            out = tmp;
        }

        if(stringLen % 2 == 1)
            for(int i = 0;i < arr.length;i++)
                out[i] = in[i];

        for(String o :in)
            System.out.println(o);
    }

    public static void main(String[] args) {
        String[] arr = {"zace","wedw","pjdr","kdet","sdet"};
        countingRadixSort(arr,4);

        int j = 1;
        for (int i = 1; i <= 100; i++) {
            System.out.print(i+" ");

            if(j == 2 ){
                System.out.println();
            }
            if(j ==5){
                System.out.println();
                j = 0;
            }
            j++;
        }
    }

    public static int getDivisor(int i ){
        if(i % 2 == 0)
            return 2;
        else
            return 5;
    }
}
