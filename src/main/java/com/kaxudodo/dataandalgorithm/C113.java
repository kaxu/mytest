package com.kaxudodo.dataandalgorithm;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by aaron on 16/8/10.
 * 创建一个泛型collection
 */
public class  C113<T>  {
    
    private Object[] os =  new Object[10];

    private int size = 0;
    
    public boolean isEmpty() {
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void makeEmpty(){
        for (int i = 0; i < size; i++) {
            os[i] = null;
        }
        os =  new Object[10];
        size=0;
    }

    public boolean insert(T o) {
        if(isFull())
            expansion();
        os[size]= (Object) o;
        size++;
        return false;
    }

    public boolean remove() {
        os[size-1] = null;
        size--;
        return true;
    }

    public boolean isPresent(T o) {
        for (int i = 0; i < size; i++) {
            if(o.equals(os[i])){
                return true;
            }
        }
        return false;
    }

    private boolean isFull(){
        return size == os.length;
    }

    private void expansion(){
        Object[] old = os;
        Object[] newos = new Object[2*os.length];
        for (int i = 0; i < os.length; i++) {
            newos[i] = old[i];
        }
        os = newos;
    }

    public static void main(String[] args) {
        C113<Integer> c113 = new C113<>();
        System.out.println(c113.isEmpty());
        c113.insert(14);
        System.out.println(c113.isEmpty());
        c113.insert(new Integer(13));
        c113.insert(new Integer(12));
        for (int i = 0; i < 10; i++) {
            c113.insert(i);
        }
        System.out.println(c113.size());
        System.out.println(c113.isPresent(new Integer(9)));
        c113.makeEmpty();
        System.out.println(c113.size());
        System.out.println(c113.isPresent(new Integer(9)));
    }

}
