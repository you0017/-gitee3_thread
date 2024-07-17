package com.yc.thread;

public class Test12_yield {
    public static void main(String[] args) {
        YieldOne yield = new YieldOne();
        YieldOne yield1 = new YieldOne();
        Thread thread = new Thread(yield,"a");
        Thread thread1 = new Thread(yield1,"b");
        thread1.setPriority(10);
        thread.setPriority(1);
        thread.start();
        thread1.start();
    }
}

class YieldOne implements Runnable{

    @Override
    public void run() {
        if ("a".equals(Thread.currentThread().getName())){
            //让出线程使用权，进入就绪态又开始抢cpu
            Thread.yield();
            //sleep是让出cpu，一段时间后再开始争抢
            //yield是让出cpu，马上开始争抢
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}
