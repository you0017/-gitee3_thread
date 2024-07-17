package com.yc.thread;


/**
 * 一级线程组
 */
public class Test14_threadgroup {
    public static void main(String[] args) {
        TestThread task1 = new TestThread();
        TestThread task2 = new TestThread();

        ThreadGroup threadGroup = new ThreadGroup("新建线程组1");

        //创建两个线程与任务绑定
        Thread t1 = new Thread(threadGroup,task1);
        Thread t2 = new Thread(threadGroup,task2);
        t1.start();
        t2.start();

        System.out.println("活动线程数为："+threadGroup.activeCount());
        System.out.println("线程组的名称："+threadGroup.getName());

        //线程组中断，则这个组的所有线程全部中断
        threadGroup.interrupt();
    }
}

class TestThread implements Runnable{

    @Override
    public void run() {
        try {
            //判断当前线程的中断信号
            while (!Thread.currentThread().isInterrupted())
            {
                System.out.println("线程名："+Thread.currentThread().getName());
                Thread.sleep(3000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
