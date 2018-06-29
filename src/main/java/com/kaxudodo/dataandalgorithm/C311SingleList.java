package com.kaxudodo.dataandalgorithm;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by aaron on 16/8/17.
 */
public class C311SingleList<T> implements Iterable<T>{

    private int theSize = 0;

    private Node<T> beginMarker;

    private int modCount = 0;

    public C311SingleList(){
        clear();
    }

    private void clear(){
        beginMarker = new Node<>(null,null);
        theSize = 0;
    }

    public int size(){
        return theSize;
    }

    @Override
    public Iterator<T> iterator() {
        return new SingleLinkedListIterator();
    }

    public void printAll(){
        Iterator iterator = iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
    }

    public boolean contains(T t){
        if(size() == 0)
            return false;
        Iterator iterator = iterator();
        while (iterator.hasNext()){
            if(iterator.next().equals(t)){
                return true;
            }
        }
        return false;
    }

    public T addNoContain(T t){
        if(!contains(t))
            add(t);
        return t;
    }

    public boolean remove(T t){
        if(size() == 0)
            return false;
        Iterator iterator = iterator();
        Node<T> last = beginMarker;
        Node<T> less = beginMarker;
        boolean ist = false;
        while (iterator.hasNext() && !ist){
            T old = (T) iterator.next();
            less = last;
            last = last.next;
            if(old.equals(t)){
                ist = true;
            }
        }
        //正好是最后一个节点
        if(ist){
            if(last.next == null){
                less.next = null;
            }else {
                less.next = last.next;
            }
            last.d = null;
            theSize--;
            modCount++;
        }
        return ist;
    }

    public T add(T t){
        Iterator iterator = iterator();
        Node<T> last = beginMarker.next;
        if(last == null){
            last = new Node<>(t,beginMarker);
            theSize++;
            modCount++;
            return t;
        }
        last = beginMarker;
        while (iterator.hasNext()){
            iterator.next();
            last = last.next;
        }
        Node<T> newNode = new Node<>(t,last);
        theSize++;
        modCount++;
        return t;
    }

    private class SingleLinkedListIterator implements Iterator<T>{

        private int current = 0;

        private Node<T> node = beginMarker.next;

        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if(!hasNext())
                throw new NoSuchElementException();
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            T t = node.d;
            node = node.next;
            current++;
            return t;
        }

        @Override
        public void remove() {

        }
    }

    private static class Node<T>{
        private Node<T> next;
        private T d;

        public Node(T t,Node<T> p){
            if(p != null)
                p.next = this;
            d = t;
            next = null;
        }

    }

    public static void main(String[] args) {
        C311SingleList<String> list = new C311SingleList<>();
        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        list.add("dddd");
        list.printAll();
        System.out.println("contains cccc "+list.contains("cccc"));
        System.out.println("remove aa "+list.remove("aa"));
        System.out.println("remove cccc "+list.remove("cccc"));
        System.out.println("contains cccc "+list.contains("cccc"));
        System.out.println("contains dddd "+list.contains("dddd"));
        System.out.println("contains ddd1d "+list.contains("ddd1d"));
    }
}
