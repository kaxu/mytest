package com.kaxudodo.datastructure.heap;

import com.kaxudodo.mysort.MyQuickSort;

import java.util.Arrays;

public class BinomialQueue<AnyType extends Comparable<? super AnyType>> {

    public BinomialQueue(){

    }

    public BinomialQueue(AnyType item){
        currentSize++;
        theTrees = new Node[currentSize];
        Node node = new Node(item,null,null);
        theTrees[0] = node;
    }

    private static class Node<AnyType>{

        Node(AnyType theElement){
            this(theElement,null,null);
        }

        Node(AnyType theElement,Node<AnyType> lt,Node<AnyType> nt){
            element=theElement;leftChild=lt;nextSibling=nt;
        }

        AnyType element;
        Node<AnyType> leftChild;
        Node<AnyType> nextSibling;
    }

    private static final int default_trees =1;
    private int currentSize;
    private Node<AnyType>[] theTrees;

    private int capacity(){
        return (1<<theTrees.length) -1;
    }

    private Node<AnyType> combingTrees(Node<AnyType> t1,Node<AnyType> t2){
        if(t1.element.compareTo(t2.element) >0)
            return combingTrees(t2,t1);
        t2.nextSibling = t1.leftChild;
        t1.leftChild=t2;
        return t1;
    }

    private void expandTheTrees(int newNumTrees){

    }

    public void merge(BinomialQueue<AnyType> rhs){
        if(this == rhs)
            return;
        currentSize += rhs.currentSize;

        if(currentSize > capacity()){
            int maxLength = Math.max(theTrees.length,rhs.theTrees.length);
            expandTheTrees(maxLength+1);
        }

        Node<AnyType> carry = null;
        for (int i = 0,j=1; j <=currentSize ; i++,j*=2) {
            Node<AnyType> t1 = theTrees[i];
            Node<AnyType> t2 = i <rhs.theTrees.length?rhs.theTrees[i]:null;

            int whichCase = t1 == null ? 0:1;
            whichCase += t2 == null ? 0:2;
            whichCase += carry == null ? 0:4;

            switch (whichCase){
                case 0:/* no trees */
                case 1:/* only this */
                    break;
                case 2: /* only rhs */
                    theTrees[i] =t2;
                    rhs.theTrees[i] = null;
                    break;
                case 4:/*only carry*/
                    theTrees[i] = carry;
                    carry=null;
                    break;
                case 3:/*this and rhs*/
                    carry = combingTrees(t1,t2);
                    theTrees[i] = rhs.theTrees[i] = null;
                    break;
                case 5:/*this and carry*/
                    carry = combingTrees(t1,carry);
                    theTrees[i] = null;
                    break;
                case 6:/*rhs and carry*/
                    carry = combingTrees(t2,carry);
                    rhs.theTrees[i] = null;
                    break;
                case 7:/*All three*/
                    theTrees[i] = carry;
                    carry = combingTrees(t1,t2);
                    rhs.theTrees[i] = null;
                    break;
            }
        }

        for (int k = 0; k < rhs.theTrees.length; k++) {
            rhs.theTrees[k]=null;
        }
        rhs.currentSize=0;
    }

    public int findMinIndex(){
        Node<AnyType>[] copyTrees = new Node[currentSize];
        for (int i = 0; i < currentSize; i++) {
            copyTrees[i]=theTrees[i];
        }
        MyQuickSort.quicksort((AnyType[])copyTrees);
        for (int i = 0; i < currentSize; i++) {
            if(copyTrees[0].equals(theTrees[i]))
                return i;
        }
        return 0;
    }

    public boolean isEmpty(){
        return currentSize==0;
    }

    public AnyType deleteMin(){
        if(isEmpty())
            throw new RuntimeException();

        int minIndex = findMinIndex();
        AnyType minItem = theTrees[minIndex].element;
        Node<AnyType> deletedTree = theTrees[minIndex].leftChild;

        BinomialQueue<AnyType> deleteQueue = new BinomialQueue<>();
        deleteQueue.expandTheTrees(minIndex+1);
        for (int j = minIndex-1; j >=0 ; j--) {
            deleteQueue.theTrees[j]=deletedTree;
            deletedTree=deletedTree.nextSibling;
            deleteQueue.theTrees[j].nextSibling=null;
        }

        theTrees[minIndex] = null;
        currentSize-=deleteQueue.currentSize+1;
        merge(deleteQueue);
        return minItem;
    }

    public void insert(AnyType x){
        merge(new BinomialQueue<>(x));
    }

    public static void main(String[] args) {
        int a[] = {1,2,3};
        int b[] = a;
        a[0]=9;
        System.out.println(Arrays.toString(b));
    }
}
