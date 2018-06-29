package com.kaxudodo.staticclass;

/**
 * Created by aaron on 16/8/20.
 */
public class MySingleLinkedList<T> {

    int theSize = 0;

    private Node<T> currentNode;

    private static class Node<T>{
        public T d;
        public MySingleLinkedList.Node<T> next;
        boolean isDel = false;
        public Node(T t, MySingleLinkedList.Node<T> n){
            d=t;next=n;
        }
    }

    public void push(T t){
        Node<T> node = new Node<>(t,currentNode);
        currentNode = node;
        theSize++;
    }

    public T pop(){
        T t = currentNode.d;
        currentNode = currentNode.next;
        theSize--;
        return t;
    }

    public static void main(String[] args) {
        MySingleLinkedList<Integer> list = new MySingleLinkedList<>();
        list.push(1);
        list.push(2);
        System.out.println(list.pop());
        System.out.println(list.pop());
    }
}
