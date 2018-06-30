package com.kaxudodo.datastructure.base;

import java.util.NoSuchElementException;

/**
 * Created by aaron on 16/8/18.
 */
public class MyStack<T> {

    private T[] theItems = (T[]) new Object[10];

    private int theSize;

    public void push(T t){
        if(theItems.length == size())
            ensureCapacity(size()*2+1);
        theItems[size()] = t;
        theSize++;
    }

    public T pop(){
        T pop = theItems[size()-1];
        theSize--;
        return pop;
    }


    public int size(){
        return theSize;
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

    public static void main(String[] args)  {
//        MyStack<Integer> myStack = new MyStack<>();
//        myStack.push(1);
//        myStack.push(12);
//        myStack.push(13);
//        int lastSize = myStack.size();
//        for (int i = 0; i < lastSize; i++) {
//            System.out.println(myStack.pop());
//        }

//        MyStack<String> myStack = new MyStack<>();
//        String[] a = {"[","]","(",")"};
//        for (int i = 0; i < a.length; i++) {
//            myStack.push(a[i]);
//        }
//        int lastSize = myStack.size();
//        for (int i = 0; i < lastSize; i++) {
//            System.out.println(myStack.pop());
//        }
        String[] a = {"[","(",")","]"};
        checkMark(a);
    }

    public static void checkMark(String[] a)   {
        MyStack<String> myStack = new MyStack<>();
        for (int i = 0; i < a.length; i++) {
            if("[".equals(a[i]) || "(".equals(a[i]))
                myStack.push(a[i]);
            if("]".equals(a[i]) || ")".equals(a[i])){
                if(myStack.size() == 0)
                    throw new NoSuchElementException("无开放符号");
                if("]".equals(a[i])){
                    String temp = myStack.pop();
                    if(temp != "[")
                        throw new NoSuchElementException("对应开放符号异常");
                }
                if(")".equals(a[i])){
                    String temp = myStack.pop();
                    if(temp != "(")
                        throw new NoSuchElementException("对应开放符号异常");
                }
            }
        }
        if(myStack.size() != 0)
            throw new NoSuchElementException("有多余开放符号");
        System.out.println("正确输入符号");
    }

    public T find(int idx){
        if(idx >= theSize)
            return null;
        T t = theItems[idx];
        for (int i = idx; i < theSize-1; i++) {
            theItems[i] = theItems[i+1];
        }
        theItems[theSize-1] =t;
        return t;
    }
}
