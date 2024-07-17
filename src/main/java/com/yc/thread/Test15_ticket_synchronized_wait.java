package com.yc.thread;

import java.util.Random;

public class Test15_ticket_synchronized_wait {
    public static void main(String[] args) {
        SellTicketOp sellTicketOp = new SellTicketOp(100);
        for (int i = 0; i < 24; i++) {
            new Thread(sellTicketOp, String.valueOf(i)).start();
        }
    }
}


//售票任务
class SellTicketOp implements Runnable {
    int ticket;

    public SellTicketOp(int ticket) {
        this.ticket = ticket;
    }

    @Override
    //synchronized public void run() {
    public void run() {
        while (true && !Thread.currentThread().isInterrupted()) {
            synchronized (this) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "出售第" + (ticket--) + "张票");
                    try {
                        //Thread.sleep(100);
                        this.wait(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    return;
                }
            }
        }
    }
}
