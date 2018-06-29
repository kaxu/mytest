package com.kaxudodo.dataandalgorithm;

/**
 * Created by aaron on 16/9/8.
 */
public class C434 {

    private static class BinaryNode<AnyType> {
        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement,
                   C434.BinaryNode<AnyType> lt, C434.BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element;
        C434.BinaryNode<AnyType> left;
        C434.BinaryNode<AnyType> right;
    }

    private static BinaryNode makeRandomTree(int lower,int upper){
        BinaryNode t = null;
        int randomValue;
        if(lower <= upper)
            t = new BinaryNode(randomValue = randInt(lower,upper),
                    makeRandomTree(lower,randomValue-1),makeRandomTree(randomValue+1,upper));
        return t;
    }

    private static BinaryNode makeRandomTree(int n){
        return makeRandomTree(1,n);
    }

    private static int randInt(int lower,int upper){
        //TODO 返回间隔稳定的随机整数
        return 0;
    }
}
