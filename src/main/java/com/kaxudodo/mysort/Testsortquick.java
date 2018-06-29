package com.kaxudodo.mysort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 2018/4/16.
 */
public class Testsortquick {

    public static void sort (List<Integer> items){
        if (items.size() > 1){
            List<Integer> smaller =new ArrayList<>();
            List<Integer> same =new ArrayList<>();
            List<Integer> larger =new ArrayList<>();

            Integer chosenItem = items.get(items.size()/2);
            for( Integer i:items){
                if(i < chosenItem)
                    smaller.add(i);
                else if(i> chosenItem)
                    larger.add(i);
                else
                    same.add(i);
            }

            sort(smaller);
            sort(larger);

            items.clear();
            items.addAll(smaller);
            items.addAll(same);
            items.addAll(larger);
        }
        System.out.println(items);
    }

    public static void main(String[] args) {
        List<Integer> a =new ArrayList<>();
        a.add(2);
        a.add(23);
        a.add(42);
        a.add(88);
        a.add(4);
        a.add(8);
        a.add(12);

        sort(a);
    }
}
