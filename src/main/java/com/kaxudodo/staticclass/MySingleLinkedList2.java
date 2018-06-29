package com.kaxudodo.staticclass;

/**
 * Created by aaron on 16/8/20.
 */
public class MySingleLinkedList2<T> {

    int theSize = 0;

    private Node<T> currentNode;

    private Node<T> beginNode;

    private static class Node<T>{
        public T d;
        public MySingleLinkedList2.Node<T> next;
        public Node(T t, MySingleLinkedList2.Node<T> p){
            d=t;
            if(p != null)
                p.next = this;
        }
    }

    public void enqueue(T t){
        Node<T> node = new Node<>(t,currentNode);
        if(currentNode == null)
            beginNode = node;
        currentNode = node;
        theSize++;
    }

    public T dequeue(){
        T t = beginNode.d;
        beginNode = beginNode.next;
        theSize--;
        return t;
    }

    public static void main(String[] args) {
        MySingleLinkedList2<Integer> list = new MySingleLinkedList2<>();
        list.enqueue(1);
        list.enqueue(2);
        list.enqueue(3);
        System.out.println(list.dequeue());
        System.out.println(list.dequeue());
        System.out.println(list.dequeue());
        System.out.println(3%7);
    }
}
