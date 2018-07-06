package com.kaxudodo.datastructure.hash;

import com.kaxudodo.utils.HashUtil;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 2018/7/6.
 */
public class MyHashFamily implements HashFamily{

    private List<HashParam> list ;

    @Override
    public int hash(Object x, int which) {
        HashParam hashParam = list.get(which);
        int hashValue = HashUtil.universalHash(x.hashCode(),hashParam.getA(),hashParam.getB());
        return hashValue;
    }

    @Override
    public int getNumberOfFunctions() {
        return list==null?0:list.size();
    }

    @Override
    public void generateNewFunctions() {
        HashParam hashParam = new HashParam();
        hashParam.setA(RandomUtils.nextInt(1,20));
        hashParam.setB(RandomUtils.nextInt(1,20));
        if(list == null)
            list = new ArrayList<>();
        list.add(hashParam);
    }


    private class HashParam{
        private int a;
        private int b;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }
    }
}
