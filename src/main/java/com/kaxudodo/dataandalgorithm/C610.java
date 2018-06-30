package com.kaxudodo.dataandalgorithm;

import com.kaxudodo.datastructure.heap.BinaryHeap;
import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;

public class C610<Integer> {

    int count = 0 ;

    private void countAdd(){
        count++;
    }

    public BinaryHeap getRandomBh(){
        BinaryHeap binaryHeap = new BinaryHeap<>();
        for (int i = 0; i < 20; i++) {
            binaryHeap.insert(RandomUtils.nextInt(1,100));
        }
        return binaryHeap;
    }

    public void getValueSmaller(BinaryHeap binaryHeap,int x){
        int i = 1;
        countAdd();
        if(binaryHeap.getArray()[i].compareTo(x) > 0 )
            return;
        else{
            System.out.println(binaryHeap.getArray()[i]);
            soutValueSmaller(binaryHeap,i,x);
        }
    }

    public void soutValueSmaller(BinaryHeap binaryHeap,int subRoot,int x){
        int left = subRoot*2;
        if(left > binaryHeap.getCurrentSize())
            return;
        if(binaryHeap.getArray()[left].compareTo(x) < 0){
            countAdd();
            System.out.println(binaryHeap.getArray()[left]);
            soutValueSmaller(binaryHeap,left,x);
        }

        int right = left+1;
        if(right > binaryHeap.getCurrentSize())
            return;
        if(binaryHeap.getArray()[right].compareTo(x) < 0){
            countAdd();
            System.out.println(binaryHeap.getArray()[right]);
            soutValueSmaller(binaryHeap,right,x);
        }


    }


    public static void main(String[] args) {
        C610 c610 = new C610();
        BinaryHeap binaryHeap = c610.getRandomBh();
        binaryHeap.setZeroNull();
        System.out.println(binaryHeap.getArray()[0]);
        System.out.println(Arrays.toString(binaryHeap.getArray()));
        c610.getValueSmaller(binaryHeap,30);
        System.out.println("***********************");
        System.out.println(c610.count);
    }
}
