package com.kaxudodo.datastructure.hash;

/**
 * Created by aaron on 2018/7/6.
 */
public interface HashFamily<AnyType> {

    int hash(AnyType x,int which);
    int getNumberOfFunctions();
    void generateNewFunctions();
}
