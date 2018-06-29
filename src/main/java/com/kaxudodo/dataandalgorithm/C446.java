package com.kaxudodo.dataandalgorithm;


/**
 * Created by aaron on 16/9/18.
 */
public class C446<T> {

    private static class AvlNode<T>{
        AvlNode(T theElement){
            this(theElement,null,null);
        }

        AvlNode(T theElement, C446.AvlNode<T> lt, C446.AvlNode<T> rt){
            element = theElement;left = lt; right = rt;height =0;
        }

        T element;
        C446.AvlNode<T> left;
        C446.AvlNode<T> right;
        int height;
    }

    public static boolean similar(AvlNode t1,AvlNode t2){
        if(t1 == null || t2 == null){
            return t1 == null && t2 == null;
        }
        return similar(t1.left,t2.left) && similar(t1.right,t2.right);
    }
}
