package com.kaxudodo.threads.threadsafe;

/**
 * Created by aaron on 2018/8/2.
 */
public class ResourceFactory {

    private static class ResourceHolder{

        ResourceHolder(){
            System.out.println("new ResourceHolder");
        }

        public static ResourceTest resource = new ResourceTest();
    }

//    static {
//        System.out.println("int static code block");
//        getResource();
//    }

    public static ResourceTest getResource(){
        return ResourceHolder.resource;
    }

    public static void main(String[] args) {
//        ResourceTest t = ResourceFactory.getResource();
//        System.out.println(t);
//        new ResourceHolder();
    }

}
