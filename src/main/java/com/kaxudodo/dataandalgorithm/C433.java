package com.kaxudodo.dataandalgorithm;

/**
 * Created by aaron on 16/9/8.
 */
public class C433 {

    private static class BinaryNode<AnyType> {
        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement,
                   C433.BinaryNode<AnyType> lt, C433.BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element;
        C433.BinaryNode<AnyType> left;
        C433.BinaryNode<AnyType> right;
    }

    static BinaryNode removeLeaves(BinaryNode t){
        if(t == null || (t.left == null && t.right == null))
            return null;
        t.left = removeLeaves(t.left);
        t.right = removeLeaves(t.right);
        return t;
    }
}
