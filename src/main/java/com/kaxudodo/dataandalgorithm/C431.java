package com.kaxudodo.dataandalgorithm;

import com.kaxudodo.staticclass.MyTreeSet2;

/**
 * Created by aaron on 16/9/8.
 */
public class C431<AnyType extends Comparable<? super AnyType>>   {

    private static class BinaryNode<AnyType> {
        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement,
                   C431.BinaryNode<AnyType> lt, C431.BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element;
        C431.BinaryNode<AnyType> left;
        C431.BinaryNode<AnyType> right;
    }

    public static int countNode(BinaryNode t){
        if(t == null)
            return 0;
        return 1+ countNode(t.left) + countNode(t.right);
    }

    public static int countLeaves(BinaryNode t){
        if(t == null){
            return 0;
        }else if(t.left == null && t.right == null)
            return 1;
        return countLeaves(t.left) + countLeaves(t.right);
    }

    public static int countFull(BinaryNode t){
        if(t == null)
            return 0;
        int tIsFull = (t.left != null && t.right != null) ? 1:0;
        return tIsFull + countFull(t.left) + countFull(t.right);
    }

}
