package com.yc.thread;

/**
 * 线程死锁
 */
public class Test16_deadlock implements Runnable{
    public int flag = 1;
    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) {
        Test16_deadlock deadlock = new Test16_deadlock();
        Test16_deadlock deadlock2 = new Test16_deadlock();

        deadlock.flag=1;
        deadlock2.flag=0;
        Thread t1 = new Thread(deadlock);
        Thread t2 = new Thread(deadlock2);
        t1.start();
        t2.start();
    }

    @Override
    public void run(){
        System.out.println("flag="+flag);
        if (flag==1){
            synchronized (o1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /*synchronized (o2){
                    System.out.println("1");
                }*/
            }
            synchronized (o2){
                System.out.println("1");
            }
        }
        if (flag==0){
            synchronized (o2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /*synchronized (o1){
                    System.out.println("2");
                }*/
            }
            synchronized (o1){
                System.out.println("2");
            }
        }
    }
}
