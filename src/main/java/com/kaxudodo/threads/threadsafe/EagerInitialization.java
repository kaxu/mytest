package com.kaxudodo.threads.threadsafe;

/**
 * Created by aaron on 2018/8/2.
 */
public class EagerInitialization {

    private static ResourceTest resourceTest = new ResourceTest();

    public static ResourceTest getInstance(){
        return resourceTest;
    }

    public static void main(String[] args) {
        ResourceTest t = EagerInitialization.getInstance();
        System.out.println(t);
        ResourceTest t1 = EagerInitialization.getInstance();
        System.out.println(t1);

        System.out.println(System.getProperty("sun.arch.data.model"));
    }

}
