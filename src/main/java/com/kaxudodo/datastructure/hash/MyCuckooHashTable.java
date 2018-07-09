package com.kaxudodo.datastructure.hash;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aaron on 2018/7/6.
 */
public class MyCuckooHashTable<AnyType> {

    MyCuckooHashTable(){
        this(DEFAULT_TABLE_SIZE);
    }

    MyCuckooHashTable(int size){
        allocateArray(nextPrime(size));
        doClear();
        hashFamily.generateNewFunctions();
    }

    private HashFamily<AnyType> hashFamily=new MyHashFamily(DEFAULT_TABLE_NUMBER);

    private static final double MAX_LOAD = 0.4;
    private static int DEFAULT_TABLE_SIZE = 3;
    private static int ALLOWED_REHASHES = 1;
    private static int DEFAULT_TABLE_NUMBER = 2;

//    private AnyType[] array1;
//    private AnyType[] array2;

    private AnyType[][] arrays;

    private int currentSize;

    public boolean insert(AnyType x){
        if(contains(x))
            return false;
        return insert1(x);
    }

    public boolean contains(AnyType x){
        for(int[] a:findPos(x)){
            if(a[0] != -1)
                return true;
        }
        return false;
    }

    private int[][] findPos(AnyType x){
        int[][] a = new int[2][1];
        a[0][0] = -1;
        a[1][0] = -1;
        return a;
    }

    private int rehashes = 0;

    public boolean insert1(AnyType x){
        final AnyType y = x;
        while (true){
            do{
                //在每个数组间循环插入
                for (int i = 0; i < DEFAULT_TABLE_NUMBER; i++) {
                    int hashValue = myhash(x,i);
                    if(arrays[i][hashValue] == null){
                        arrays[i][hashValue] = x;
                        currentSize++;
                        return true;
                    }else{
                        AnyType tmp = arrays[i][hashValue];
                        arrays[i][hashValue] = x;
                        x = tmp;
                    }
                }
            }while (!y.equals(x));

            if(++rehashes>ALLOWED_REHASHES){
                expand();
                rehashes = 0;
            }else {
                rehash();
            }
        }

    }

    private void expand(){
        System.out.println("expand");
        rehash((int)(totalLength()/MAX_LOAD));
    }

    private int totalLength(){
        int totalLength = 0;
//        for (int i = 0; i < DEFAULT_TABLE_NUMBER; i++) {
//            totalLength += arrays[i].length;
//        }
        totalLength += arrays[0].length;
        return totalLength;
    }

    private void rehash(){
        hashFamily.generateNewFunctions();
        rehash(totalLength());
    }

    private void rehash(int newLength){
        System.out.println("rehash-----"+newLength);
        AnyType[][] oldArray = arrays;
        allocateArray(nextPrime(newLength));
        currentSize = 0;

        for(AnyType[] as:oldArray)
            for(AnyType a:as){
                if(a!=null)
                    insert(a);
            }

    }

    private void allocateArray(int arraySize){

        arrays = (AnyType[][])new Object[DEFAULT_TABLE_NUMBER][];
        for (int i = 0; i < DEFAULT_TABLE_NUMBER; i++) {
            arrays[i] = (AnyType[]) new Object[arraySize];
        }
    }

    private void doClear(){
        currentSize = 0;
        for (int i = 0; i < DEFAULT_TABLE_NUMBER; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                arrays[i][j] = null;
            }
        }
    }

    private int myhash(AnyType x,int which){
        int hashVaule = hashFamily.hash(x,which);
        hashVaule %=arrays[0].length;
        if(hashVaule < 0 )
            hashVaule +=arrays[0].length;
        return hashVaule;
    }

    private static int nextPrime(int n){
        BigInteger bi1 = new BigInteger(String.valueOf(n));
        // assign nextProbablePrime value of bi1 to bi2
        BigInteger bi2 = bi1.nextProbablePrime();
        return bi2.intValue();
    }

    private void showArrays(){
        for (int i = 0; i < DEFAULT_TABLE_NUMBER; i++) {
            System.out.println("------------"+i+"--------------");
            for (int j = 0; j < arrays[i].length; j++) {
                if(arrays[i][j] != null)
                    System.out.print(j+"-"+arrays[i][j].toString()+" ");
            };
            System.out.println("------------");
        }

        System.out.println("*********test***********");
        for(String a :Arrays.asList(new String[]{"A", "G", "N"})){
            System.out.println("-------"+a+"---------------");
            for (int i = 0; i < DEFAULT_TABLE_NUMBER; i++) {
                System.out.println(myhash((AnyType) a,i));
            }
        }

        System.out.println("*********test2***********");
        int count = 0;
        for (int i = 0; i < DEFAULT_TABLE_NUMBER; i++) {
            for(AnyType a:arrays[i]){
                if(a!=null)
                    count++;
            }
        }
        System.out.println("test main string length:"+count);

    }


    public static void main(String[] args) {
        String[] strings = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N"};
        System.out.println("main string length:"+strings.length);
        MyCuckooHashTable<String> hashTable = new MyCuckooHashTable<>();
        for(String a:strings)
            hashTable.insert(a);

        hashTable.showArrays();


    }

}
