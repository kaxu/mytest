package com.kaxudodo.threads;

/**
 * Created by aaron on 2018/7/26.
 */
public class JoinPlayerThread  extends Thread{
    public String name ;
    public int time ;
    public JoinPlayerThread(String name, int time){
        this.name = name ;
        this.time = time ;
    }
    
    public void run(){
        try{
            Thread.sleep(time);

        }catch(Exception e){
        }
        System.out.println(name + "用了" + time + "时间到了");
    }

    public static void main(String[] args) throws InterruptedException {
        JoinPlayerThread p1 = new JoinPlayerThread("李小龙",5);
        JoinPlayerThread p2 = new JoinPlayerThread("成龙",10);
        JoinPlayerThread p3 = new JoinPlayerThread("马龙",1);
        JoinPlayerThread p4 = new JoinPlayerThread("狄龙",15);

        p1.start();
        p2.start();
        p3.start();
        p4.start();

        p1.join();
        p2.join();
        p3.join();
        p4.join();

        //下面这段代码需要等到p1,p2,p3,p4线程全部执行完毕之后才会别调用
        System.out.println("人到齐了，开始打麻将！！！");
    }
}



