package com.kaxudodo.threads;

/**
 * Created by aaron on 2018/7/26.
 */
public class SellTicket {
    public static void main(String[] args) {
        Ticket t = new Ticket();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
    }
}
class Ticket implements Runnable {

    private int ticket = 100;

    public void run() {
        while (ticket > 0) {
            ticket--;
            System.out.println("当前线程"+Thread.currentThread().getName()+" 当前票数为：" + ticket);
        }

    }

}