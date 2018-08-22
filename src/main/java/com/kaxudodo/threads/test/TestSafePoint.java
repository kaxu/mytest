package com.kaxudodo.threads.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by aaron on 2018/8/15.
 */
public class TestSafePoint {

    private int x,y;

    public TestSafePoint(int x ,int y){
        System.out.println("construct");
        Thread thread = new Thread(this.new T(this));
        thread.start();
        this.x =x;
        this.y =y;
        System.out.println("construct end1");
    }

    public synchronized void set(int x,int y){
        System.out.println("set");
        this.x = x;
        this.y = y;
    }

    public class T implements Runnable{
        private TestSafePoint tp;

        public T(TestSafePoint tp){
            this.tp = tp;
        }

        @Override
        public void run() {
            tp.set(5,5);
            System.out.println("run end");
        }
    }

    public static void main(String[] args) {
        TestSafePoint testSafePoint = new TestSafePoint(3,3);
        System.out.println("construct end2");

//        Thread thread = new Thread(testSafePoint.new T(testSafePoint));
//
//        thread.start();
//                try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(testSafePoint.x+" "+testSafePoint.y);
    }



}
