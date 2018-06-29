package com.kaxudodo.dataandalgorithm;

import com.kaxudodo.staticclass.MyLinkedList;

import java.util.Iterator;

/**
 * Created by aaron on 16/8/15.
 */
public class C304  {

    public static void test34(MyLinkedList<Integer> l1,MyLinkedList<Integer> l2){
        Iterator it1 = l1.iterator();
        Iterator it2 = l2.iterator();
        Integer int1 = null;
        Integer int2 = null;
        if(it1.hasNext() && it2.hasNext()){
            int1 = (Integer) it1.next();
            int2 = (Integer) it2.next();
        }
        while (it1.hasNext() && it2.hasNext()){

            if(int1 < int2){
                int1 = (Integer) it1.next();
                continue;
            }
            if(int1 == int2){
                int1 = (Integer) it1.next();
                int2 = (Integer) it2.next();
                System.out.println(int1);
            }
            if(int1 > int2){
                int2 = (Integer) it2.next();
                continue;
            }

        }
    }

    public static void test35(MyLinkedList<Integer> l1,MyLinkedList<Integer> l2){
        Iterator it1 = l1.iterator();
        Iterator it2 = l2.iterator();
        Integer int1 = null;
        Integer int2 = null;
        if(it1.hasNext() && it2.hasNext()){
            int1 = (Integer) it1.next();
            int2 = (Integer) it2.next();
        }
        while (int1 != null && int2 != null){
            if(int1 == null && int2 != null){
                int2 = (Integer) (it2.hasNext()?it2.next():null);
                System.out.println(int2);
            }

            if(int1 != null && int2 == null){
                int1 = (Integer) (it1.hasNext()?it1.next():null);
                System.out.println(int1);
            }

            if(int1 < int2){
                int1 = (Integer) (it1.hasNext()?it1.next():null);
                System.out.println(int1);
                continue;
            }
            if(int1 == int2){
                int1 = (Integer) (it1.hasNext()?it1.next():null);
                int2 = (Integer) (it2.hasNext()?it2.next():null);
                System.out.println(int1);

            }
            if(int1 > int2){
                int2 = (Integer) (it2.hasNext()?it2.next():null);
                System.out.println(int2);
                continue;
            }

        }
    }
}
