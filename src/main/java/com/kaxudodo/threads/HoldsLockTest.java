package com.kaxudodo.threads;

public class HoldsLockTest {

    Object o = new Object();

    public void test1() throws Exception {

        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized(o) {
                    System.out.println("child thread: holdLock: " +
                            Thread.holdsLock(o));
                }
            }
        }).start();

        System.out.println("main thread: holdLock: " + Thread.holdsLock(o));
        Thread.sleep(2000);

    }

    public static void main(String[] args) throws Exception {
        HoldsLockTest holdsLockTest = new HoldsLockTest();
        holdsLockTest.test1();
    }


}
