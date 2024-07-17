package com.yc.thread;

public class Test13_join {
    public static void main(String[] args) throws InterruptedException {
        //当前是main线程
        LifeCircle lc = new LifeCircle();
        System.out.println(lc.isAlive());//线程状态值
        lc.start();
        System.out.println(lc.isAlive());

        lc.join();  //让lc先运行完再执行main

        System.out.println("主程序");
        System.out.println(lc.isAlive());
    }
}

class LifeCircle extends Thread{
    @Override
    public void run() {
        int i=0;
        while ((i++)<10){
            System.out.println(Thread.currentThread().getName()+":"+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}