package com.kaxudodo.threads.threadsafe;

/**
 * Created by aaron on 2018/8/2.
 */
public class ResourceTest {

    public ResourceTest(){
        System.out.println("new ResourceTest");
    }

    private int a;

    private String b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
