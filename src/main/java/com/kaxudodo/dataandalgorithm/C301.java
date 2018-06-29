package com.kaxudodo.dataandalgorithm;

import java.util.Iterator;
import java.util.List;

/**
 * Created by aaron on 16/8/14.
 */
public class C301 {

    public static <T> void printLots(List<T> l,List<Integer> p){
        Iterator<T> iterl = l.iterator();
        Iterator<Integer> iterp = p.iterator();
        T iteml = null;
        Integer itemp =  0;
        int start = 0;
        while (iterl.hasNext() && iterp.hasNext()){
            itemp = iterp.next();
            System.out.println("looking for position "+itemp);
            while (start < itemp && iterl.hasNext()){
                start++;
                iteml = iterl.next();
            }
            System.out.println(iteml);
        }
    }
}
