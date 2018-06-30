package com.kaxudodo.datastructure.base;

import java.nio.BufferUnderflowException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by aaron on 16/9/5.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

        private static class BinaryNode<T>{
            BinaryNode(T t){
                this(t,null,null);
            }

            BinaryNode(T t,BinaryNode<T> lt,BinaryNode<T> rt){
                element= t;left =lt;right = rt;
            }

            BinaryNode(T t,BinaryNode<T> lt,BinaryNode<T> rt,BinaryNode<T> ft){
                element= t;left =lt;right = rt;parent= ft;
            }

            T element;
            BinaryNode<T> left;
            BinaryNode<T> right;
            BinaryNode<T> parent;
        }

        private BinaryNode<T> root;

        public BinarySearchTree(){
            root = null;
        }

        public void makeEmpty(){
            root = null;
        }

        public boolean isEmpty(){
            return root == null;
        }

        public boolean contains(T x){
            return contains(x,root);
        }

        public T findMin(){
            if(isEmpty()) throw new BufferUnderflowException();
            return findMin(root).element;
        }

        public T findMax(){
            if(isEmpty()) throw new BufferUnderflowException();
            return findMax(root).element;
        }

        private boolean contains(T x,BinaryNode<T> t){
            if(t == null)
                return false;
            int compareResult = x.compareTo(t.element);
            if(compareResult < 0)
                return contains(x,t.left);
            else if(compareResult > 0)
                return contains(x,t.right);
            else
                return true;
        }

        private BinaryNode<T> findMin(BinaryNode<T> t){
            if(t == null)
                return null;
            else if(t.left == null)
                return t;
            return findMin(t.left);
        }

        private BinaryNode<T> findMax(BinaryNode<T> t){
            if(t != null)
                while (t.right != null)
                    t = t.right;
            return t;
        }

        public void insert(T x){
            root = insert(x,root,null);
        }

        public void remove(T x){
            root = remove(x,root);
        }

        public void printTreee(){
            if(isEmpty())
                System.out.println("Empty tree");
            else
                printTree(root);
        }

        private void printTree(BinaryNode<T> t){
            if(t != null){
                printTree(t.left);
                System.out.println(t.element);
                printTree(t.right);
            }
        }

        private BinaryNode<T> insert(T x,BinaryNode<T> t,BinaryNode<T> pt){
            if(t == null)
                return new BinaryNode<T>(x,null,null,pt);
            int compareResult = x.compareTo(t.element);
            if(compareResult < 0){
                t.left = insert(x, t.left,t);
            }
            else if(compareResult > 0){
                t.right = insert(x,t.right,t);
            }
            else
                ;
            return t;
        }

        private BinaryNode<T> remove(T x,BinaryNode<T> t){
            if(t == null)
                return t;
            int compareResult = x.compareTo(t.element);
            if(compareResult < 0)
                t.left = remove(x,t.left);
            else if(compareResult > 0)
                t.right =remove(x,t.right);
            else if (t.left != null && t.right != null){
                t.element = findMin(t.right).element;
                t.right = remove(t.element,t.right);
            }else{
                BinaryNode<T> oneChild;
                oneChild = (t.left != null) ? t.left : t.right;
                oneChild.parent = t.parent;
                t = oneChild;
            }
            return t;
        }

    private class MyTreeSetIterator implements Iterator<T>{

        private BinaryNode<T> current = findMin(root);
        private BinaryNode<T> previous ;
        private boolean isEnd = false;
        private boolean isOkToRemove = false;


        @Override
        public boolean hasNext() {
            return !isEnd;
        }

        @Override
        public T next() {
            if(!hasNext())
                throw new NoSuchElementException();
            T ce = current.element;
            BinaryNode<T> child = current;
            previous = current;
            if(current.right != null){
                current = current.right;
            }
            current = current.parent;
            while (current != null && current.left != child ){
                child = current;
                current = current.parent;
            }
            if(current == null)
                isEnd = true;
            isOkToRemove = true;
            return ce;
        }

        @Override
        public void remove() {
            if(!isOkToRemove)
                throw new IllegalStateException();
            BinarySearchTree.this.remove(previous.element);
            isOkToRemove = false;
        }
    }

}
