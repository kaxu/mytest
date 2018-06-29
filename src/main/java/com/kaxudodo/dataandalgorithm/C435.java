package com.kaxudodo.dataandalgorithm;

import com.kaxudodo.staticclass.MyAvlTree;

/**
 * Created by aaron on 16/9/8.
 */
public class C435<T> {

    private static class AvlNode<T>{
        AvlNode(T theElement){
            this(theElement,null,null);
        }

        AvlNode(T theElement, C435.AvlNode<T> lt, C435.AvlNode<T> rt){
            element = theElement;left = lt; right = rt;height =0;
        }

        T element;
        C435.AvlNode<T> left;
        C435.AvlNode<T> right;
        int height;
    }

    private AvlNode getAvlTree(int h,int midValue){
        AvlNode t = null;
//        int maxNode = (int) Math.pow(2,h+1)-1;
        if(h == 0){
            t = get0AvlTree(midValue-1);
        }
        if(h == 1){
            t = get1AvlTree(midValue+1);
        }
        if(h > 1){
            t = new AvlNode(midValue,null,null);
            t.left = getAvlTree(h-2,midValue/2);
            t.right = getAvlTree(h-1, (int) (midValue*1.5));
        }

        return t;
    }

    private AvlNode get0AvlTree(int maxValue){
        AvlNode t = new AvlNode(maxValue,null,null);
        return t;
    }

    private AvlNode get1AvlTree(int maxValue){
        AvlNode tr = new AvlNode(maxValue+1,null,null);
        AvlNode t = new AvlNode(maxValue,null,tr);
        return t;
    }

    static AvlNode genTree(int height,int[] lastNode){
        AvlNode t = null;
        if(height >= 0){
            t = new AvlNode(lastNode[0],null,null);
            t.left = genTree(height-1,lastNode);
            t.element = ++lastNode[0];
            t.right = genTree(height-2,lastNode);
        }
        return t;
    }

    static AvlNode minNodeTree(int h){
        int[] lastNode = {0};
        return genTree(h,lastNode);
    }
}
