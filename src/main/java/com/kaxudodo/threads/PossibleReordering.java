package com.kaxudodo.threads;

/**
 * Created by aaron on 2018/8/2.
 */
public class PossibleReordering {

    static int x = 0,y=0;
    static int a = 0,b=0;

    public static void test(){

    }

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                a = 1;
                x = b;
            }
        });
        Thread other = new Thread(new Runnable() {
            @Override
            public void run() {
                b = 1;
                y = a;
            }
        });
        one.start();other.start();
        one.join();
        other.join();
        System.out.println(x+","+y);

    }
}
