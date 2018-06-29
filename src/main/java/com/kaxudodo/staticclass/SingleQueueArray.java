package com.kaxudodo.staticclass;

import java.util.*;
public class SingleQueueArray<AnyType>
{
    SingleQueueArray()
    { this(101); }  // note:  actually holds one less than given size
    SingleQueueArray(int s)
    {
        maxSize = s;
        front = 0;
        rear = 0;
        elements = new ArrayList<AnyType>(maxSize);
    }
    void enqueue(AnyType x)
    {
        if ( !full() )
        {
            if (elements.size() < maxSize)  // add elements until size is reached
                elements.add(x);
            else
                elements.set(rear, x);  // after size is reached, use set
            rear = (rear + 1) % maxSize;
        }
    }
    AnyType dequeue()
    {
        AnyType temp=null;
        if ( !empty() )
        {
            temp = elements.get(front);
            front = (front+1) % maxSize;
        }
        return temp;
    }
    boolean empty()
    { return front == rear; }
    boolean full()
    { return (rear + 1) % maxSize == front; }
    private int front, rear;
    private int maxSize;
    private ArrayList<AnyType> elements;
}
