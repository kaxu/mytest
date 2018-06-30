package com.kaxudodo.datastructure.base;

import java.util.Arrays;

/**
 * Created by aaron on 16/8/19.
 */
public class MyDeque<T> {

    private T[] theItems = (T[]) new Object[10];

    private int theSize;

    private int endSize;

    public void inject(T t){
        if(theItems.length == size())
            ensureCapacity(size()*2+1);
        theItems[theItems.length-1-endSize] = t;
        endSize++;
    }

    public T eject(){
        T eject = theItems[theItems.length-1-(endSize-1)];
        endSize--;
        return eject;
    }

    public void push(T t){
        if(theItems.length == size())
            ensureCapacity(size()*2+1);
        theItems[theSize] = t;
        theSize++;
    }

    public T pop(){
        T pop = theItems[theSize-1];
        theSize--;
        return pop;
    }


    public int size(){
        return theSize+endSize;
    }

    public void ensureCapacity(int newCapacity){
        if(newCapacity < (theSize+endSize))
            return;
        T[] old = theItems;
        theItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < theSize; i++) {
            theItems[i] = old[i];
        }
        int tempEndSize = endSize;
        for (int i = 0; i < tempEndSize; i++) {
            theItems[theItems.length-1-i] = old[old.length-1-i];
        }
    }

    public static void main(String[] args) {
        MyDeque<Integer> deque= new MyDeque<>();
        for (int i = 1; i < 3; i++) {
            deque.push(i);
        }
        for (int i = 10; i > 7; i--) {
            deque.inject(i);
        }
        System.out.println(Arrays.toString(deque.theItems));

        for (int i = 1; i < 3; i++) {
            Integer a = deque.eject();
            System.out.println(a);
        }
        System.out.println(Arrays.toString(deque.theItems));
        for (int i = 100; i > 96; i--) {
            deque.inject(i);
        }
        System.out.println(Arrays.toString(deque.theItems));
    }

}
