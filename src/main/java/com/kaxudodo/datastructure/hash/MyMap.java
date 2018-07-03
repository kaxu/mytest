package com.kaxudodo.datastructure.hash;

/**
 * Created by aaron on 2018/7/2.
 */
public class MyMap<KeyType,ValueType> {

    private QuadraticProbingHashTable<Entry<KeyType,ValueType>> items;

    private static final int DEFAULT_TABLE_SIZE = 11;

    public MyMap(){
        this(DEFAULT_TABLE_SIZE);
    }

    public MyMap(int size){
        items = new QuadraticProbingHashTable<>(size);
    }

    public void put(KeyType key,ValueType value){

        Entry entry = new Entry(key,value);
        if(items.contains(entry)){
            items.remove(entry);
        }
        items.insert(entry);

    }

    public ValueType get(KeyType key){
        Entry entry = new Entry(key,null);
        if(items.contains(entry)){
            entry = items.find(entry);
            return (ValueType) entry.value;
        }
        return null;

    }

    private static class Entry<KeyType,ValueType>{
        KeyType key;
        ValueType value;
        public Entry(KeyType key,ValueType value){
            this.key = key;
            this.value=value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Entry<?, ?> entry = (Entry<?, ?>) o;

            return key != null ? key.equals(entry.key) : entry.key == null;

        }

        @Override
        public int hashCode() {
            return key != null ? key.hashCode() : 0;
        }
    }

    public static void main(String[] args) {
        MyMap<Integer,String> myMap = new MyMap<>(11);
        myMap.put(1,"aaa");
        myMap.put(2,"bbbb");
        System.out.println(myMap.get(1));
        System.out.println(myMap.get(2));
        myMap.put(2,"dcccc");
        System.out.println(myMap.get(2));
        System.out.println(myMap.get(3));
    }
}
