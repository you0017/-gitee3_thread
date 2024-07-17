package com.yc.thread;

public class _volatile {
    public static void main(String[] args) {
        Task task = new Task();
        Thread thread = new Thread(task);
        thread.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    task.flag = true;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread2.start();
    }
}

class Task implements Runnable{
    //内存可见
    volatile boolean flag = false;
    int i=0;
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (!flag){
            i++;
        }
        System.out.println("循环时间："+(System.currentTimeMillis()-start));
    }
}
