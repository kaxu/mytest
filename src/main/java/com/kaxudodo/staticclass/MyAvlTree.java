package com.kaxudodo.staticclass;

/**
 * Created by aaron on 16/9/6.
 */
public class MyAvlTree<T extends Comparable<? super T>> {

    private static class AvlNode<T>{
        AvlNode(T theElement){
            this(theElement,null,null);
        }

        AvlNode(T theElement,AvlNode<T> lt,AvlNode<T> rt){
            element = theElement;left = lt; right = rt;height =0;
        }

        T element;
        AvlNode<T> left;
        AvlNode<T> right;
        int height;
    }

    private int height(AvlNode<T> t){
        return t == null ? -1 : t.height;
    }

    private AvlNode<T> insert(T x,AvlNode<T> t){
        if(t == null)
            return new AvlNode<T>(x,null,null);
        int compareResult = x.compareTo(t.element);
        if(compareResult < 0)
            t.left = insert(x,t.left);
        else if(compareResult > 0)
            t.right = insert(x,t.right);
        else
            ;
        return balance(t);
    }

    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2){
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left),height(k2.right))+1;
        k1.height = Math.max(height(k1.left),k2.height)+1;
        return k1;
    }

    private AvlNode<T> rotateWithRightChild(AvlNode<T> k1){
        AvlNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left),height(k1.right))+1;
        k2.height = Math.max(height(k2.right),k1.height)+1;
        return k2;
    }

    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3){
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode<T> doubleWithRightChild(AvlNode<T> k3){
        k3.left = rotateWithLeftChild(k3.left);
        return rotateWithRightChild(k3);
    }

    private static final int ALLOWED_IMBALANCE = 1;

    private AvlNode<T> balance(AvlNode<T> t){
        if(t == null)
            return t;
        if(height(t.left) - height(t.right)  > ALLOWED_IMBALANCE)
            if(height(t.left.left) >= height(t.left.right))
                t = rotateWithLeftChild(t);
            else
                t = doubleWithLeftChild(t);
        else if(height(t.right) - height(t.left) > ALLOWED_IMBALANCE)
            if(height(t.right.right) >= height(t.right.left))
                t = rotateWithRightChild(t);
            else
                t = doubleWithRightChild(t);
        t.height = Math.max(height(t.left),height(t.right)) + 1;
        return t;
    }

}
