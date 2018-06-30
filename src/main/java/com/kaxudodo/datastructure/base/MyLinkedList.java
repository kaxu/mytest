package com.kaxudodo.datastructure.base;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Created by aaron on 16/8/7.
 */
public class MyLinkedList<T> implements Iterable<T>{

    private static class Node<T>{
        public T d;
        public Node<T> prev;
        public Node<T> next;
        boolean isDel = false;
        public Node(T t,Node<T> p,Node<T> n){
            d=t;prev=p;next=n;
        }
    }

    public void addBefore(Node<T> p,T x){
        Node<T> newNode = new Node<>(x,p.prev,p);
        newNode.prev.next =newNode;
        p.prev = newNode;

    }

    private void doclear(){
        beginMarker = new Node<>(null,null,null);
        endMarker = new Node<>(null,beginMarker,null);
        beginMarker.next = endMarker;
        theSize = 0;
        theDelSize = 0;
        modCount++;
    }

    public boolean contains(T t){
        Node begin = beginMarker;
        while (begin.next != null && begin.next != endMarker){
            if(t.equals(begin.d)){
                return true;
            }
            begin = begin.next;
        }
        return false;
    }

    public Iterator<T> iterator(){
        return new LinkedListIterator();
    }

    private class  LinkedListIterator implements Iterator<T>{

        private Node<T> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if(!hasNext())
                throw new NoSuchElementException();

            T nextItem = current.d;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {

            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if(!okToRemove)
                throw new IllegalStateException();
            // TODO
        }
    }

    private T set( Node<T> p, T newVal )
    {
        T oldVal = p.d;
        p.d = newVal;
        return oldVal;
    }

    private class MyLinkedListIterator implements ListIterator {


        boolean backwards = false;

        private Node<T> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public Object next() {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if(!hasNext()){
                throw new NoSuchElementException();
            }

            T nextItem = current.d;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public boolean hasPrevious() {
            return current != beginMarker;
        }

        @Override
        public Object previous() {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if(!hasNext()){
                throw new NoSuchElementException();
            }

            T preItem = current.prev.d;
            current = current.prev;
            okToRemove = true;
            return preItem;
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
//            if (backwards)
//                MyArrayList.this.remove( current-- );
//            else
//                MyArrayList.this.remove( --current );
        }


        @Override
        public void set(Object o) {
            if ( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException();
            MyLinkedList.this.set( current.next, (T) o);
        }

        @Override
        public void add(Object o) {
            Node newItem = new Node(o,current,current.next);
        }
    }

    private T realRemove(Node<T> p){
        p.next.prev = p.prev;
        p.prev.next = p.next;
//        theSize--;
        theDelSize--;
        modCount++;
        return p.d;
    }

    private T remove(Node<T> p){
        p.isDel = true;
        theSize--;
        theDelSize++;
        if(theSize == theDelSize){
            Node toDel = beginMarker.next;
            for (int i = 0; i < theSize+theDelSize; i++) {
                if(toDel.isDel){
                    realRemove(toDel);
                }
                toDel = toDel.next;
            }
        }
        return p.d;
    }

    public void removeAll(Iterable<? extends T> items){
        Iterator<T> tIterator = (Iterator<T>) items.iterator();
        while (tIterator.hasNext()){
            T toRemove = tIterator.next();
            Iterator<T> iteratorOld = iterator();
            while (iteratorOld.hasNext()){
                T old = iteratorOld.next();
                if(old.equals(toRemove))
                    iteratorOld.remove();
            }
        }
    }

    public void splice(Iterator<T> itr,MyLinkedList<? extends T> lst){
        itr = (MyLinkedListIterator) itr;
        if(((MyLinkedListIterator) itr).hasPrevious()){
            T preItem = (T) ((MyLinkedListIterator) itr).previous();
            Iterator<T> lstiter = (Iterator<T>) lst.iterator();
            while (lstiter.hasNext()){
                T t = lstiter.next();
                new Node<>(t,((MyLinkedListIterator) itr).current,((MyLinkedListIterator) itr).current.next);
            }
        }
    }

    public Node<T> getNode(int idx,int lower,int upper){
        Node<T> p;
        if(idx < lower || idx > upper)
            throw new IndexOutOfBoundsException();
        if(idx < size()/2){
            p = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
        }else{
            p = endMarker;
            for (int i = size(); i > idx ; i--) {
                p = p.prev;
            }
        }
        return p;
    }

    private Node<T> getNode(int idx){
        return getNode(idx,0,size()-1);
    }

    public void add(int idx,T x){
        addBefore(getNode(idx,0,size()),x);
    }

    public void addFirst(T x){
        addBefore(beginMarker.next,x);
    }

    public void addLast(T x){
        addBefore(endMarker.prev,x);
    }

    private T removeFirst(){
        return remove(beginMarker.next);
    }

    private T removeLast(){
        return remove(endMarker.prev);
    }

    private T getFirst(){
        return beginMarker.next.d;
    }

    private T getLast(){
        return endMarker.prev.d;
    }

    public int size(){
        return theSize;
    }

    private int theSize;
    private int theDelSize;
    private int modCount = 0;
    private Node<T> beginMarker;
    private Node<T> endMarker;

    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList =new MyLinkedList();
        Node<Integer> n = new Node<>(1,null,null);
        Node<Integer> n2 = new Node<>(1,null,null);
    }

}
