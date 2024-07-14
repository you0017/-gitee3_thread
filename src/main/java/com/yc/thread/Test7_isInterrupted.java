package com.yc.thread;

public class Test7_isInterrupted {
    public static void main(String[] args) {
        //当前线程
        Thread thread = Thread.currentThread();
        //检查当前线程是否被中断
        System.out.println(thread.getName() + "线程是否中断：" + thread.isInterrupted());//false
        //设置中断信号
        thread.interrupt();
        //检查当前线程是否被中断
        System.out.println(thread.getName() + "线程是否中断：" + thread.isInterrupted());//true
        //检查当前线程是否被中断
        System.out.println(thread.getName() + "线程是否中断：" + thread.isInterrupted());//true

        try {
            Thread.sleep(1000);//本来要在main线程中睡眠20S的，但因为interrupt()被调用了，所以sleep打断
            System.out.println(thread.getName()+"线程休眠未被中断");
        } catch (InterruptedException e) {//异常捕获操作也会恢复中断状态
            e.printStackTrace();
            System.out.println(thread.getName()+"线程休眠被中断");
            //判断现场是否中断，因为异常已经处理完 所以恢复状态   拓展：catch写处理代码
            System.out.println(thread.getName()+"线程是否终端："+thread.isInterrupted());//false
        }

        System.out.println(thread.getName() + "线程是否中断："+thread.isInterrupted());
    }
}
