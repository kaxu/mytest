package com.kaxudodo.datastructure.base;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by aaron on 16/8/17.
 */
public class MyArrayList<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY =10;

    private int theSize;
    private T[] theItems;

    public int size(){
        return theSize;
    }

    public MyArrayList(){
        doClear();
    }

    public void clear(){
        doClear();
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void trimToSize(){
        ensureCapacity(size());
    }

    public T get(int idx){
        if(idx < 0 || idx>=size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[idx];
    }

    public T set(int idx,T newVal){
        if(idx < 0 || idx>=size())
            throw new ArrayIndexOutOfBoundsException();
        T old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }

    public boolean add(T x){
        add(size(),x);
        return true;
    }

    public void add(int idx,T x){
        if(theItems.length == size())
            ensureCapacity(size()*2+1);
        for (int i = theSize; i > idx ; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[idx] = x;
        theSize++;
    }

    public void addAll(Iterator<? extends T> items){
        while (items.hasNext()){
            T t = items.next();
            add(size(),t);
        }

    }

    private void doClear(){
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public void ensureCapacity(int newCapacity){
        if(newCapacity < theSize)
            return;
        T[] old = theItems;
        theItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    public T remove(int idx){
        T removedItem = theItems[idx];
        for (int i = idx; i < size()-1; i++) {
            theItems[i] = theItems[i+1];
        }
        theSize--;
        return removedItem;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements ListIterator{

        /**
         * 当前项
         * 当前项的概念通过把迭代器看做是在对next的调用所给出的项和对previous的调用所有给出的项之间而抽象出来的
         */
        private int current = 0;

        boolean backwards = false;

        @Override
        public boolean hasNext() {
//            throw new UnsupportedOperationException();
//            return false;
            return current < size();
        }

        @Override
        public Object next() {
//            throw new UnsupportedOperationException();
//            return null;
            if ( !hasNext() )
                throw new java.util.NoSuchElementException();
            backwards = false;
            return theItems[ current++ ];
        }

        @Override
        public boolean hasPrevious() {
            if(current - 1 >= 0 ){
                backwards = true;
                return true;
            }
            return false;
        }

        @Override
        public Object previous() {
            if ( !hasPrevious() )
                throw new java.util.NoSuchElementException();
            return theItems[--current];
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
//            return 0;
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
//            return 0;
        }

        public void remove()
        {
            if (backwards)
                MyArrayList.this.remove( current-- );
            else
                MyArrayList.this.remove( --current );
        }


        @Override
        public void set(Object o) {
            theItems[current] = (T) o;
        }

        @Override
        public void add(Object o) {
            MyArrayList.this.add((T) o);
        }
    }
}
